package com.golftec.aws.dynamodb.util;

public class JsonStringer {
	
	public static String getJsonFormattedString(String stringToFormat) {
		stringToFormat = stringToFormat.replace("=", "");
		stringToFormat = stringToFormat.replace("{N: ", ":");
		stringToFormat = stringToFormat.replace("{S: ", ":");
		stringToFormat = stringToFormat.replace("{M: ", ":");
		stringToFormat = stringToFormat.replace(",}", "");
		stringToFormat = stringToFormat.replace(" ", "");
		stringToFormat = stringToFormat.replaceAll("[\\x00-\\x09\\x11\\x12\\x14-\\x1F\\x7F]", "");
		return stringToFormat;
	}

}
