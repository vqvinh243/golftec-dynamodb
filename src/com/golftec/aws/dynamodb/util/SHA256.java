package com.golftec.aws.dynamodb.util;

import java.security.MessageDigest;

public class SHA256 {
	
	public static String getSHA256Hash(String text) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		return new String(hash, "UTF-8");
	}

}
