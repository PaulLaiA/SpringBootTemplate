package org.dpaul.template.springboot.exception;

public enum ErrorCode {

	SUCCESS(0, "OK", ""),
	UNPREDICTABLE(90000, "Unpredictable Error", "未經定義的錯誤"),
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