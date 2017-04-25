package com.datayes.webspider.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtil {
	private static MessageDigest md;
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("md5 initial - e={}", e);
		}
	}

	public static String encryptByMD5(String str) {
		try {
			byte[] hash = md.digest(str.getBytes("UTF-8"));
			return Base64.encodeBase64String(hash);
		} catch (UnsupportedEncodingException e) {
			logger.error("encryptByMD5 - args:str={},e={}", str, e);
		}
		return null;
	}
}
