package org.dpaul.template.springboot.common;

import lombok.extern.slf4j.Slf4j;
import org.dpaul.template.springboot.common.response.Result;
import org.dpaul.template.springboot.common.response.ResultStatus;
import org.dpaul.template.springboot.exception.BusinessException;
import org.dpaul.template.springboot.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller 全局異常處理中心
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(BusinessException.class)
	public Result<Void> businessExceptionHandler(BusinessException e) {
		log.error("BusinessException: " + e.getMessage(), e);
		return e.getCode() == ErrorCode.SUCCESS.getCode() ?
				Result.success(ResultStatus.SUCCESS, null) :
				Result.failure(ResultStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public Result<Void> runtimeExceptionHandler(RuntimeException e) {
		log.error("RuntimeExceptionHandler: " + e.getMessage(), e);
		return Result.failure(ResultStatus.INTERNAL_SERVER_ERROR);
	}
}