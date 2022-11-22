package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.common.exception.AuthenticationEx;
import com.blackdotan.elephant.security.annotation.*;
import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author black an
 * @date 2022/11/22
 */
public class RequireAccessTokenVerifier extends IVerifier {

    public RequireAccessTokenVerifier(IVerifier chain) {
        super(chain);
    }

    /**
     * @param method
     * @param authentication
     * @return
     */
    @Override
    public boolean verify(HttpServletRequest request, Method method, SimpleUserAuthentication authentication) {

        if (method.isAnnotationPresent(RequireAccessToken.class)
                || (method.isAnnotationPresent(Require.class) && (method.getAnnotation(Require.class).accessToken()))) {
            if (authentication == null) {
                throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Token未授权（找不到认证对象）");
            }
        }

        return doChain(request, method, authentication);
    }
}
