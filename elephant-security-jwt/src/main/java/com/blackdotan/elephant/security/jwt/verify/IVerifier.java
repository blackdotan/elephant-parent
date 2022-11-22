package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author black an
 * @date 2022/11/22
 */
public abstract class IVerifier {
    /**
     *
     */
    protected IVerifier chain;

    public IVerifier(IVerifier chain) {
        this.chain = chain;
    }

    /**
     * @param request
     * @param method
     * @param authentication
     * @return
     */
    public abstract boolean verify(HttpServletRequest request, Method method, SimpleUserAuthentication authentication);

    /**
     * @param request
     * @param method
     * @param authentication
     * @return
     */
    protected boolean doChain(HttpServletRequest request, Method method, SimpleUserAuthentication authentication) {
        if (chain != null) {
            return chain.verify(request, method, authentication);
        }
        return true;
    }
}
