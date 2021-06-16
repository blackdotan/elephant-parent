package com.ipukr.elephant.security.jwt.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ipukr.elephant.security.jwt.JWTAuthentication;
import com.ipukr.elephant.security.jwt.utils.JWTUtilities;
import com.ipukr.elephant.security.jwt.utils.UrlTemplateUtilities;
import com.ipukr.elephant.common.exception.AuthenticationEx;
import com.ipukr.elephant.common.exception.IllegalAccessEx;
import com.ipukr.elephant.security.annotation.*;
import com.ipukr.elephant.security.rabc.AccessAuthority;
import com.ipukr.elephant.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Web权限拦截器 / 拦截所有请求
 *
 * @author ryan
 *
 * Created by ryan on 下午6:28.
 */
@Slf4j
public class JWTWebSecurityFilterV2 implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Token";

    @Value("#{'${setting.web.jwt.url.exclude:}'.split(',')}")
    private Set<String> iExcludeUrls;

    @Value("${wants.web.jwt.secret:ipukrsecret}")
    private String secret;

    private Algorithm algorithm ;



    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256(secret);
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String path = request.getServletPath();

        String m = request.getMethod();
        String TOKEN = request.getHeader(AUTHORIZATION_HEADER);
        String cxt = request.getContentType();

        StringBuffer parameters = new StringBuffer();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = request.getParameter(key);
            parameters.append(key).append("=").append(value).append(",");
        }

        StringBuffer header = new StringBuffer();
        Enumeration hnu = request.getHeaderNames();
        while (hnu.hasMoreElements()) {
            String key = (String) hnu.nextElement();
            String value = request.getHeader(key);
            header.append(key).append("=").append(value).append(",");
        }

        log.debug("拦截网络请求, 地址:{}, 请求方法:{}, 请求参数:{}, TOKEN={}, SESSION.ID={}, 头信息:{}, Content-Type:{}",
                path,
                m,
                parameters.toString(),
                TOKEN,
                request.getSession().getId(),
                header,
                cxt);

        /**
         * 判断是否是白名单地址
         * */
        if (iExcludeUrls.stream().filter(e -> !e.trim().equals("") && new UriTemplate(e.trim()).matches(path)).count()>0) {
            log.debug("拦截验证通过（白名单）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
            return true;
        } else {
            log.debug("拦截验证验证（非白名单）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
            if (handler instanceof ResourceHttpRequestHandler) {
                log.debug("拦截验证通过（静态资源）.0, 地址:{}, 请求方法:{}, TOKEN={}; ", path, m, TOKEN);
                return true;
            } else if (handler instanceof HandlerMethod) {
                log.debug("拦截验证验证（非静态资源）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
                Method method = ((HandlerMethod) handler).getMethod();

                /**
                 * A1: 请求白名单/不拦截
                 * */
                if (method.isAnnotationPresent(RequireWhite.class)) {
                    log.debug("拦截验证鉴权（放行地址）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
                    return true;
                } else {
                    log.debug("拦截验证鉴权. 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
                    if (TOKEN == null || TOKEN.equals("")) {
                        throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "登录状态已失效，请重新登录");
                    }
                    DecodedJWT jwt = JWTUtilities.decode(TOKEN, algorithm);
                    Date expire = jwt.getExpiresAt();
                    if (DateUtils.now().after(expire)) {
                        // TODO 406
                    }

                    JWTAuthentication authentication = JWTUtilities.convert(jwt);

                    request.setAttribute(JWTAuthentication.NAME, authentication);

                    /**
                     * 1.1)判断 Subject 类型
                     * */
                    if (method.isAnnotationPresent(RequireSubject.class)
                            || method.isAnnotationPresent(Require.class)) {
                        String[] subjects = method.isAnnotationPresent(RequireSubject.class) ?
                                method.getAnnotation(RequireSubject.class).value() :
                                    method.isAnnotationPresent(Require.class) ?
                                            method.getAnnotation(Require.class).subjects(): new String[0];
                        if (!Arrays.asList(subjects).contains(authentication.getSubject()) ) {
                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Subject:{}, 无该接口权限", authentication.getSubject());
                        }
                    }

                    /**
                     * 1.2)判断 Audience 类型
                     * */
                    if (method.isAnnotationPresent(RequireAudience.class)
                            || method.isAnnotationPresent(Require.class)) {
                        String[] audiences = method.isAnnotationPresent(RequireAudience.class) ?
                                method.getAnnotation(RequireAudience.class).value() :
                                method.isAnnotationPresent(Require.class) ?
                                        method.getAnnotation(Require.class).audiences(): new String[0];
                        boolean flag = false;
                        // 判断是否包含
                        for (String audience : audiences) {
                            for (String aud : authentication.getAudience()) {
                                if (audience.equals(aud)) {
                                    flag = true;
                                }
                            }
                        }
                        // 非真，未授权
                        if (!flag) {
                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Audience:{}, 无该接口权限", authentication.getAudience());
                        }
                    }


                    /**
                     * 2)判断Token认证
                     * */
                    if (method.isAnnotationPresent(RequireAccessToken.class) ||
                            ( method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).accessToken())) {
                        /**
                         * TODO
                         * A2-1: 判断客户端是否授权
                         *
                         * */
//                        if (client == null) {
//                            log.debug("客户端未授权（TOKEN不存在）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
//                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Client未授权（Header.Token 不存在）");
//                        }
                        if (authentication == null) {
                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Token未授权（找不到认证对象）");
                        }
                    }

//                    /**
//                     * 3)判断客户端类型
//                     * */
//                    if(method.isAnnotationPresent(RequireType.class)) {
////                        if (client == null || client.getType() == null || !SecurityUtils.contain(method.getAnnotation(RequireType.class).value(), client.getType())) {
////                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "客户端受限，当前客户端无访问该接口权限");
////                        }
//
//                    }
//                    if (method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).types().length > 0 ) {
////                        if (client == null || client.getType() == null || !SecurityUtils.contain(method.getAnnotation(Require.class).types(), client.getType())) {
////                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "客户端受限，当前客户端无访问该接口权限");
////                        }
//                    }

                    /**
                     * 4)判断登录认证
                     * */
                    if (method.isAnnotationPresent(RequireLogin.class) ||
                            ( method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).login())) {
//                        if ( client == null || !client.authorized()) {
//                            log.debug("用户未登录, 地址:{}, 请求方法:{}, TOKEN={}", path, m, TOKEN);
//                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，用户未登录");
//                        }
                        if (authentication == null) {
                            throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Token未授权（找不到认证对象）");
                        }
                    }

                    /**
                     * 5)判断授权
                     * */
                    if (method.isAnnotationPresent(RequirePermission.class)||
                            ( method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).permission())) {
                        String aliasUri = path;
                        String aliasMethod = m;
                        // TODO
                        if (method.isAnnotationPresent(RequirePermission.class)) {
                            RequirePermission rp = method.getAnnotation(RequirePermission.class);
                            if (!rp.uri().equals("")) {
                                aliasUri = rp.uri();
                            }
                            if (!rp.method().equals("")) {
                                aliasMethod = rp.method();
                            }
                        }
                        if (aliasMethod.equals(m)) {

//                            Set<String> permissions = authentication.getPermissions().stream()
//                                    .filter(e->e.getUrlTemplate()!=null && !e.getUrlTemplate().trim().equals(""))
//                                    .map(e->e.getUrlTemplate())
//                                    .collect(Collectors.toSet());
//                            if (authentication == null ||  !UrlTemplateUtilities.valid(authentication.getUrls(), path)) {
//                            authentication.getAuthorities().stream().map(e->e.)

                            AccessAuthority access = new AccessAuthority();
                            access.setMethod(request.getMethod());
                            access.setUrl(request.getServletPath());

//                            if( authentication == null ||  AuthentificationUtilities.isauthorised(authentication, access)) {

//                            UrlTemplateUtilities.valid()
                            List<AccessAuthority> accesses = authentication.getAuthorities();

                            if( authentication == null ||  UrlTemplateUtilities.valid(accesses, access)) {
//                            if (authentication == null ||  !UrlTemplateUtilities.valid(, path)) {
                                log.debug("URL未授权, 地址:{}, 请求方法:{}, TOKEN={}", path, m, TOKEN);
                                throw new IllegalAccessEx(HttpStatus.FORBIDDEN, "权限受限，无访问该接口权限");
                            }
                        } else {
                            throw new IllegalAccessEx(HttpStatus.FORBIDDEN, "权限受限，无访问该接口权限");
                        }
                    }

                    /**
                     * 请求成功
                     * */
                    log.debug("拦截验证通过（校验通过）, 地址:{}, 请求方法:{}, TOKEN={};", path, m, TOKEN);
                    return true;
                }
            } else {
                log.debug("拦截验证通过（静态资源）, 地址:{}, 请求方法:{}, TOKEN={}; ", path, m, TOKEN);
                return true;
            }
        }
    }

}
