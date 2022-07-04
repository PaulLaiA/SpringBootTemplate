package org.dpaul.template.springboot.exception;


public class BusinessException extends GlobalException {


	public BusinessException(String message, int code, String description) {
		super(message, code, description);
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode);
	}

	public BusinessException(ErrorCode errorCode, String description) {
		super(errorCode, description);
	}

}