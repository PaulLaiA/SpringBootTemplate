package org.dpaul.template.springboot.common;

public enum ErrorCode {

	SUCCESS(0, "OK", ""),
	;

	private final int code;
	private final String message;
	private final String description;

	ErrorCode(int code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
}