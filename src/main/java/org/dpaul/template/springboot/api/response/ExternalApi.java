package org.dpaul.template.springboot.api.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 继承{@link ResponseBody}的自定义API Result格式ResponseBody
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ExternalApi {

}