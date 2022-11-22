package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.common.exception.AuthenticationEx;
import com.blackdotan.elephant.security.annotation.Require;
import com.blackdotan.elephant.security.annotation.RequireRole;
import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 需要角色
 * Created by ryan on 上午10:21.
 */
public class RequireRoleVerifier extends IVerifier {

    public RequireRoleVerifier(IVerifier chain) {
        super(chain);
    }

    /**
     * @param request
     * @param method
     * @param authentication
     * @return
     */
    @Override
    public boolean verify(HttpServletRequest request, Method method, SimpleUserAuthentication authentication) {

        if ( method.isAnnotationPresent(RequireRole.class) || (method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).roles().length > 0) ) {
            boolean flag = false;
            String[] require = new String[0];
            if (method.isAnnotationPresent(RequireRole.class)) {
                RequireRole rr = method.getAnnotation(RequireRole.class);
                require = rr.value();
            } else if (method.isAnnotationPresent(Require.class)) {
                Require rr = method.getAnnotation(Require.class);
                require = rr.roles();
            }
            for (String s : require) {
                for (Serializable role : authentication.getRoles()) {
                    if (s.equals(role)) {
                        flag = true;
                    }
                }
            }
            if (!flag) {
                throw new AuthenticationEx(HttpStatus.FORBIDDEN, "请求失败，权限受限，无访问该接口权限");
            }
        }

        return doChain(request, method, authentication);
    }
}
