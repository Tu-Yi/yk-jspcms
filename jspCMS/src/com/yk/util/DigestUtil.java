package com.yk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	public static String getMD5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data);
		byte[] resultBytes = md.digest();
		String resultString = fromBytesToString(resultBytes);
		return resultString;
	}

	public static String getSHA1(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(data);
		byte[] resultBytes = md.digest();
		String resultString = fromBytesToString(resultBytes);
		return resultString;
	}

	public static String fromBytesToString(byte[] resultBytes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < resultBytes.length; i++) {
			if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
				builder.append("0").append(Integer.toHexString(0xFF & resultBytes[i]));
			} else {
				builder.append(Integer.toHexString(0xFF & resultBytes[i]));
			}
		}
		return builder.toString();
	}

}
