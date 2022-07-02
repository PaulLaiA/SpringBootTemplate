package org.dpaul.template.springboot.common.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * The enum Result status.
 */
@ToString
@Getter
public enum ResultStatus {

	/**
	 * Success result status.
	 */
	SUCCESS(HttpStatus.OK, 200, "OK"),
	/**
	 * The Bad request.
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
	/**
	 * The Internal server error.
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "服務器發生異常,請通知管理員進行查看"),

	;

	/**
	 * 返回的HTTP状态码,  符合http请求
	 */
	private final HttpStatus httpStatus;
	/**
	 * 业务异常码
	 */
	private final Integer code;
	/**
	 * 业务异常信息描述
	 */
	private final String message;

	ResultStatus(HttpStatus httpStatus, Integer code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}