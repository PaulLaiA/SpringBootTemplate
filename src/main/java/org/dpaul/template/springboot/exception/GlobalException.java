package org.dpaul.template.springboot.exception;

import lombok.Getter;
import org.dpaul.template.springboot.common.ErrorCode;

@Getter
public class GlobalException extends RuntimeException {
	protected final int code;

	protected final String description;

	public GlobalException(String message, int code, String description) {
		super(message);
		this.code = code;
		this.description = description;
	}

	public GlobalException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.description = errorCode.getDescription();
	}

	public GlobalException(ErrorCode errorCode, String description) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.description = description;
	}


}