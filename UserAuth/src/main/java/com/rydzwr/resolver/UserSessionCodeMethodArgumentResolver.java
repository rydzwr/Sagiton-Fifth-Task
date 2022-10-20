package com.rydzwr.resolver;

import com.rydzwr.model.UserCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

public class UserSessionCodeMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserCode.class) && !Map.class.isAssignableFrom(parameter.nestedIfOptional().getNestedParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserCode ann = parameter.getParameter().getAnnotation(UserCode.class);
        return webRequest.getHeader(ann.value());
    }
}
