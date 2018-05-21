package com.ipukr.elephant.common.web.binding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipukr.elephant.common.EnumFactory;
import com.ipukr.elephant.common.Identifiable;
import com.ipukr.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import java.lang.annotation.*;
import java.lang.reflect.Type;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/21.
 */
public class IdentifiableParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver implements WebArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(IdentifiableParamMethodArgumentResolver.class);

    private ObjectMapper mapper = new ObjectMapper();

    public IdentifiableParamMethodArgumentResolver() {
        super(null);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(IdentifiableParam.class)) {
            return true;
        }
        return false;
    }

    @Override
    protected AbstractNamedValueMethodArgumentResolver.NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        IdentifiableParam annotation = parameter.getParameterAnnotation(IdentifiableParam.class);
        return new IdentifiableParamMethodArgumentResolver.IdentifiableParamNamedValueInfo(annotation);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest nwr) throws Exception {
        String[] values = nwr.getParameterValues(name);
        Class<Identifiable> clazz = (Class<Identifiable>) parameter.getParameterType();

        logger.debug("捕获Identifiable格式参数, name={}, parameter={}, nwr={}, values={}, paramType={}", name, parameter, nwr, values, clazz);
        if (values == null) {
            logger.warn("警告:捕获Identifiable格式参数, name={}, values=null ");
            return null;
        } else {
            if (values.length == 1) {
                String text = values[0];
                return EnumFactory.prop(clazz, values[0]);
            }
        }
        throw new UnsupportedOperationException("too many request json parameter '" + name + "' for method parameter type [" + clazz + "], only support one json parameter");
    }


    @Override
    protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
        IdentifiableParam annotation = parameter.getParameterAnnotation(IdentifiableParam.class);
        if( annotation.required()) {
            String paramType = parameter.getParameterType().getName();
            throw new ServletRequestBindingException("Missing request json parameter '" + paramName + "' for method parameter type [" + paramType + "]");
        }
    }



    private class IdentifiableParamNamedValueInfo extends AbstractNamedValueMethodArgumentResolver.NamedValueInfo {

        private IdentifiableParamNamedValueInfo() {
            super("", false, null);
        }

        private IdentifiableParamNamedValueInfo(IdentifiableParam annotation) {
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
