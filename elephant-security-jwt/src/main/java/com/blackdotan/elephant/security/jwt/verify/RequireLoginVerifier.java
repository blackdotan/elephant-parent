package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.common.exception.AuthenticationEx;
import com.blackdotan.elephant.security.annotation.*;
import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 需要登录
 * Created by ryan on 上午10:21.
 */
public class RequireLoginVerifier extends IVerifier {

    public RequireLoginVerifier(IVerifier chain) {
        super(chain);
    }

    @Override
    public boolean verify(HttpServletRequest request, Method method, SimpleUserAuthentication authentication) {
        /**
         * 4)判断登录认证
         * */
        if ( method.isAnnotationPresent(RequireLogin.class)
                || (method.isAnnotationPresent(Require.class) && (method.getAnnotation(Require.class).login())) ) {
            if (authentication == null) {
                throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Token未登录（找不到认证对象）");
            }
        }

        return doChain(request, method, authentication);
    }
}
