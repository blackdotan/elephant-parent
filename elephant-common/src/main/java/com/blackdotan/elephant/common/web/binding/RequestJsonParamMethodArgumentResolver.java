package com.blackdotan.elephant.common.web.binding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.blackdotan.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import java.lang.reflect.Type;


/**
 *
 * 解析请求参数json字符串
 *
 * @author Zhang Kaitao
 * @since 3.1
 */
public class RequestJsonParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver implements WebArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(RequestJsonParamMethodArgumentResolver.class);

    private ObjectMapper mapper = new ObjectMapper();

	public RequestJsonParamMethodArgumentResolver() {
		super(null);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	    if (parameter.hasParameterAnnotation(RequestJsonParam.class)) {
		    return true;
		}
		return false;
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
	    RequestJsonParam annotation = parameter.getParameterAnnotation(RequestJsonParam.class);
		return new RequestJsonParamNamedValueInfo(annotation);
	}

	@Override
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest nwr) throws Exception {
		String[] values = nwr.getParameterValues(name);
        Class<?> clazz = parameter.getParameterType();
        logger.debug("捕获Json格式参数, name={}, parameter={}, nwr={}, values={}, paramType={}", name, parameter, nwr, values, clazz);
        if (values == null) {
            logger.warn("警告:捕获Json格式参数, name={}, values=null ");
            return null;
        } else {
            if (values.length == 1) {
                String text = values[0];
                Type type = parameter.getGenericParameterType();
                return JsonUtils.parserString2Obj(values[0], clazz);
            }
        }
        throw new UnsupportedOperationException("too many request json parameter '" + name + "' for method parameter type [" + clazz + "], only support one json parameter");
	}


	@Override
	protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
		RequestJsonParam annotation = parameter.getParameterAnnotation(RequestJsonParam.class);
		if( annotation.required()) {
			String paramType = parameter.getParameterType().getName();
			throw new ServletRequestBindingException("Missing request json parameter '" + paramName + "' for method parameter type [" + paramType + "]");
		}
	}



	private class RequestJsonParamNamedValueInfo extends NamedValueInfo {

		private RequestJsonParamNamedValueInfo() {
			super("", false, null);
		}

		private RequestJsonParamNamedValueInfo(RequestJsonParam annotation) {
			super(annotation.value(), annotation.required(), null);
		}
	}

	/**
	 * spring 3.1之前
	 */
    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest request) throws Exception {
        if(!supportsParameter(parameter)) {
            return WebArgumentResolver.UNRESOLVED;
        }
        return resolveArgument(parameter, null, request, null);
    }
}
