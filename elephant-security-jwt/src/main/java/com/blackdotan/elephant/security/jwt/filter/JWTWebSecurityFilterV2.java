package com.blackdotan.elephant.security.jwt.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blackdotan.elephant.security.annotation.*;
import com.blackdotan.elephant.security.jwt.JWTAuthenticationV2;
import com.blackdotan.elephant.security.jwt.utils.JWTUtilitiesV2;
import com.blackdotan.elephant.security.jwt.verify.*;
import com.blackdotan.elephant.security.jwt.JWTAuthentication;
import com.blackdotan.elephant.security.jwt.utils.JWTUtilities;
import com.blackdotan.elephant.common.exception.AuthenticationEx;
import com.blackdotan.elephant.utils.DateUtils;
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

    // public static final String AUTHORIZATION_HEADER = "Token";

    @Value("#{'${elephant.security.jwt.url.exclude:}'.split(',')}")
    private Set<String> iExcludeUrls;
    @Value("${elephant.security.jwt.secret:ipukrsecret}")
    private String secret;
    @Value("${elephant.security.jwt.authorization.header:Token}")
    private String authorizationHeader;


    private Algorithm algorithm ;

    private IVerifier verifier;

    @PostConstruct
    private void init() {
        // JWT 算法
        algorithm = Algorithm.HMAC256(secret);
        // 构建权限验证链
        verifier = new RequireSubjectVerifier(
                new RequireAccessTokenVerifier(
                        new RequireLoginVerifier(
                                new RequireRoleVerifier(
                                        new RequirePermissionVerifier(null)
                                )
                        )
                )
        );
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
        String TOKEN = request.getHeader(authorizationHeader);
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
        if (iExcludeUrls.stream().filter(e -> !e.trim().equals("")
                && new UriTemplate(e.trim()).matches(path)).count()>0) {
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

                    JWTAuthenticationV2 authentication = JWTUtilitiesV2.decode(TOKEN, algorithm);
                    request.setAttribute(JWTAuthentication.NAME, authentication);

                    // 拦截验证
                    verifier.verify(request, method, authentication);

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
