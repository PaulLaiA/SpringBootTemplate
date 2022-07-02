package org.dpaul.template.springboot.common.response;

import lombok.Getter;
import lombok.ToString;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 */
@Getter
@ToString
public class Result<T> {
	/**
	 * 业务错误码
	 */
	private final Integer code;
	/**
	 * 信息描述
	 */
	private final String message;
	/**
	 * 返回参数
	 */
	private final T data;

	private Result(ResultStatus resultStatus, T data) {
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
		this.data = data;
	}

	/**
	 * 业务成功返回业务代码和描述信息
	 *
	 * @return Result result
	 */
	public static Result<Void> success() {
		return new Result<>(ResultStatus.SUCCESS, null);
	}

	/**
	 * 业务成功返回业务代码,描述和返回的参数
	 *
	 * @param <T>  the type parameter
	 * @param data the data
	 *
	 * @return result result
	 */
	public static <T> Result<T> success(T data) {
		return new Result<>(ResultStatus.SUCCESS, data);
	}

	/**
	 * 业务成功返回业务代码,描述和返回的参数
	 *
	 * @param <T>          the type parameter
	 * @param resultStatus the result status
	 * @param data         the data
	 *
	 * @return the result
	 */
	public static <T> Result<T> success(ResultStatus resultStatus, T data) {
		if (resultStatus == null) {
			return success(data);
		}
		return new Result<>(resultStatus, data);
	}

	/**
	 * 业务异常返回业务代码和描述信息
	 *
	 * @return the result
	 */
	public static Result<Void> failure() {
		return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
	}

	/**
	 * 业务异常返回业务代码,描述和返回的参数
	 *
	 * @param <T>          the type parameter
	 * @param resultStatus the result status
	 *
	 * @return the result
	 */
	public static <T> Result<T> failure(ResultStatus resultStatus) {
		return failure(resultStatus, null);
	}

	/**
	 * 业务异常返回业务代码,描述和返回的参数
	 *
	 * @param <T>          the type parameter
	 * @param resultStatus the result status
	 * @param data         the data
	 *
	 * @return the result
	 */
	public static <T> Result<T> failure(ResultStatus resultStatus, T data) {
		if (resultStatus == null) {
			return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
		}
		return new Result<>(resultStatus, data);
	}
}