package com.datayes.webspider.util;

import java.util.regex.Pattern;

public class ParamValidateUtil {
	private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9.]{5,16}$");
	private static final Pattern PWD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,20}$");

	public static boolean validateEmail(String email) {
		if (email == null) {
			return false;
		}
		return EMAIL_PATTERN.matcher(email).find();
	}

	public static boolean validateNickname(String nickname) {
		if (nickname == null) {
			return false;
		}
		return NICKNAME_PATTERN.matcher(nickname).find();
	}

	public static boolean validatePwd(String pwd) {
		if (pwd == null) {
			return false;
		}
		return PWD_PATTERN.matcher(pwd).find();
	}

}
