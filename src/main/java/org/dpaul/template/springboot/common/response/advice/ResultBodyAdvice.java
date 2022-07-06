package org.dpaul.template.springboot.common.response.advice;

import lombok.extern.slf4j.Slf4j;
import org.dpaul.template.springboot.common.response.Result;
import org.dpaul.template.springboot.common.response.annotation.ResponseResultBody;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * The type Response result body advice.
 */
@Slf4j
@RestControllerAdvice
public class ResultBodyAdvice implements ResponseBodyAdvice<Object> {

	private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

	/**
	 * 判断类或者方法是否使用了 @ResponseResultBody
	 *
	 * @param returnType    the return type
	 * @param converterType the selected converter type
	 *
	 * @return 是否
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(),
		                                           ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
	}

	/**
	 * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
	 *
	 * @param body                  the body to be written
	 * @param returnType            the return type of the controller method
	 * @param selectedContentType   the content type selected through content negotiation
	 * @param selectedConverterType the converter type selected to write to the response
	 * @param request               the current request
	 * @param response              the current response
	 *
	 * @return 包裝后的returnVal
	 */
	@Override
	public Object beforeBodyWrite(Object body,
	                              MethodParameter returnType,
	                              MediaType selectedContentType,
	                              Class<? extends HttpMessageConverter<?>> selectedConverterType,
	                              ServerHttpRequest request, ServerHttpResponse response) {
		// 防止重复包裹的问题出现
		if (body instanceof Result) {
			return body;
		}
		return Result.success(body);
	}
}