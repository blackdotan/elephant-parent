package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author black an
 * @date 2022/11/22
 */
public class RequireSubjectVerifier extends IVerifier {

    public RequireSubjectVerifier(IVerifier chain) {
        super(chain);
    }

    @Override
    public boolean verify(HttpServletRequest request, Method method, SimpleUserAuthentication authentication) {
//        if (method.isAnnotationPresent(RequireSubject.class)
//                || method.isAnnotationPresent(Require.class)) {
//            String[] subjects = method.isAnnotationPresent(RequireSubject.class) ?
//                    method.getAnnotation(RequireSubject.class).value() :
//                    method.isAnnotationPresent(Require.class) ?
//                            method.getAnnotation(Require.class).subjects(): new String[0];
//            if (!Arrays.asList(subjects).contains(authentification.getSubject()) ) {
//                throw new AuthenticationEx(HttpStatus.UNAUTHORIZED, "请求失败，Subject:{}, 无该接口权限", authentification.getSubject());
//            }
//        }
        return doChain(request, method, authentication);
    }
}
