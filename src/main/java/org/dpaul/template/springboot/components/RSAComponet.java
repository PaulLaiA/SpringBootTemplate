package org.dpaul.template.springboot.components;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
@Scope
public final class RSAComponet {

	private final RSA rsa = new RSA();
	@Value("classpath:REAKeys/N.txt")
	private Resource N;
	@Value("classpath:REAKeys/e.txt")
	private Resource e;
	@Value("classpath:REAKeys/d.txt")
	private Resource d;

	@PostConstruct
	public void init() {
		rsa.setPrivateKey(RSA.generatePrivateKey(Str2BigInt(N), Str2BigInt(d)));
		rsa.setPublicKey(RSA.generatePublicKey(Str2BigInt(N), Str2BigInt(e)));
	}

	/**
	 * 加密
	 *
	 * @param obj 物件
	 *
	 * @return 加密字符
	 */
	public String encode(Object obj) {
		String str = JSONUtil.toJsonStr(obj);
		final byte[] bytes = StrUtil.bytes(str, StandardCharsets.UTF_8);
		byte[] encrypt = rsa.encrypt(bytes, KeyType.PrivateKey);
		return HexUtil.encodeHexStr(encrypt);
	}

	/**
	 * 解密
	 *
	 * @param str 加密字符
	 *
	 * @return 解密字符
	 */
	public String dencode(String str) {
		byte[] bytes = HexUtil.decodeHex(str);
		byte[] decrypt = rsa.decrypt(bytes, KeyType.PublicKey);
		return StrUtil.str(decrypt, StandardCharsets.UTF_8);
	}

	private BigInteger Str2BigInt(Resource resource) {
		try {
			return new BigInteger(Files.readAllLines(resource.getFile().toPath()).get(0));
		} catch (IOException ex) {
			return new BigInteger("0");
		}
	}

}