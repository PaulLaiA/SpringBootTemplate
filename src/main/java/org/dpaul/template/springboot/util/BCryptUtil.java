package org.dpaul.template.springboot.util;


import cn.hutool.crypto.digest.BCrypt;

public class BCryptUtil {
	private static final Integer LOG_ROUND = 12;

	/**
	 * 通過JBCrypt加密
	 *
	 * @param content 加密内容
	 *
	 * @return 加密完的Hash
	 */
	public static String getHash(String content) {
		return BCrypt.hashpw(content, BCrypt.gensalt(LOG_ROUND));
	}

	/**
	 * 比較是否一致
	 *
	 * @param content 未加密内容
	 * @param hashed  加密後的密碼
	 *
	 * @return 是否一致
	 */
	public static Boolean checkHash(String content, String hashed) {
		return BCrypt.checkpw(content, hashed);
	}
}