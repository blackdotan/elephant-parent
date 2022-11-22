package com.blackdotan.elephant.security.jwt.verify;

import com.blackdotan.elephant.common.exception.IllegalAccessEx;
import com.blackdotan.elephant.security.annotation.Require;
import com.blackdotan.elephant.security.annotation.RequirePermission;
import com.blackdotan.elephant.security.jwt.utils.UrlTemplateUtilities;
import com.blackdotan.elephant.security.rabc.AccessAuthority;
import com.blackdotan.elephant.security.rabc.SimpleUserAuthentication;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 需要判断权限
 * Created by ryan on 上午10:21.
 */
public class RequirePermissionVerifier extends IVerifier {
	/**
	 * @param chain
	 */
	public RequirePermissionVerifier(IVerifier chain) {
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
		String path = request.getServletPath();
		String m = request.getMethod();
		if (method.isAnnotationPresent(RequirePermission.class)||
				( method.isAnnotationPresent(Require.class) && method.getAnnotation(Require.class).permission())) {
			String aliasUri = path;
			String aliasMethod = m;
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
				AccessAuthority access = new AccessAuthority();
				access.setMethod(request.getMethod());
				access.setUrl(request.getServletPath());
				List<AccessAuthority> accesses = authentication.getAuthorities();
				if( !UrlTemplateUtilities.valid(accesses, access)) {
					throw new IllegalAccessEx(HttpStatus.FORBIDDEN, "权限受限，无访问该接口权限");
				}
			} else {
				throw new IllegalAccessEx(HttpStatus.FORBIDDEN, "权限受限，无访问该接口权限");
			}
		}
		return doChain(request, method, authentication);
	}
}
