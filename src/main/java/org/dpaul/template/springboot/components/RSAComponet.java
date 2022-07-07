package org.dpaul.template.springboot.components;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Scope
@Slf4j
public class RSAComponet {

	private final RSA rsa = new RSA();
	@Value("classpath:RSAKeys/N.txt")
	private Resource N;
	@Value("classpath:RSAKeys/e.txt")
	private Resource e;
	@Value("classpath:RSAKeys/d.txt")
	private Resource d;

	@Value("classpath:RSAKeys/secret.txt")
	private Resource secret;

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
	public String encrypt(Object obj) {
		String str = JSONUtil.toJsonStr(obj);
		final byte[] bytes = StrUtil.bytes(str, StandardCharsets.UTF_8);
		byte[] encrypt = rsa.encrypt(bytes, KeyType.PrivateKey);
		return HexUtil.encodeHexStr(encrypt);
	}

	public void generateKey(String secret, int keySize) throws NoSuchAlgorithmException {
		int DEFAULT_KEY_SIZE = 4096;
		String KEY_ALGORITHM = "RSA";
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), new SecureRandom(secret.getBytes()));
		final KeyPair keyPair = keyPairGenerator.genKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		final String N = rsaPrivateKey.getModulus().toString();
		final String d = rsaPrivateKey.getPrivateExponent().toString();
		final String e = rsaPublicKey.getPublicExponent().toString();
		log.info("N : " + N);
		log.info("d : " + d);
		log.info("e : " + e);
	}

	/**
	 * 解密
	 *
	 * @param str 加密字符
	 *
	 * @return 解密字符
	 */
	public String decrypt(String str) {
		byte[] bytes = HexUtil.decodeHex(str);
		byte[] decrypt = rsa.decrypt(bytes, KeyType.PublicKey);
		return StrUtil.str(decrypt, StandardCharsets.UTF_8);
	}

	public PublicKey getPublicKey() {
		return rsa.getPublicKey();
	}

	public PrivateKey getPrivateKey() {
		return rsa.getPrivateKey();
	}

	private BigInteger Str2BigInt(Resource resource) {
		try {
			return new BigInteger(new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8));
		} catch (IOException ex) {
			return new BigInteger("0");
		}
	}

}