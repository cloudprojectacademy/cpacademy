package com.cpacademy.core.cpa.util;

public class CPATokenCipher {

	public static final String DEFAULT_KEY = "cpa2015";

	private static CPACipher getTokenCipher(String sKey) throws Exception {
		return CPACipher.getInstance(sKey.getBytes(), true /* use padding */);
	}


	public static String rawDecrypt(String sKey, String token) throws Exception {
		return CPATokenCipher.rawDecrypt(sKey, token, null);
	}

	public static String rawDecrypt(String sKey, String token, String encoding) throws Exception {

		if (sKey == null || sKey.length() == 0) {
			sKey = CPATokenCipher.DEFAULT_KEY;
		}

		CPACipher pvoCipher = CPATokenCipher.getTokenCipher(sKey);

		try {
			return pvoCipher.decrypt(token, encoding);
		} catch (Exception e) {
			throw e;
		}
	}

	public static String rawEncrypt(String sKey, String dataToEncrypt) throws Throwable {
		return CPATokenCipher.rawEncrypt(sKey, dataToEncrypt, null);
	}

	public static String rawEncrypt(String sKey, String dataToEncrypt, String encoding) throws Throwable {

		if (sKey == null || sKey.length() == 0) {
			sKey = CPATokenCipher.DEFAULT_KEY;
		}
		CPACipher tokenCipher = CPATokenCipher.getTokenCipher(sKey);

		return tokenCipher.encrypt(dataToEncrypt, encoding);
	}

}
