package com.cpacademy.core.cpa.common;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpacademy.core.cpa.context.AppContextHolder;
import com.cpacademy.core.cpa.exception.CPAException;
import com.cpacademy.core.cpa.util.CPATokenCipher;
import com.cpacademy.core.cpa.util.StringUtil;

public class UserToken implements Serializable {
	
	private static Logger logger = LoggerFactory.getLogger(UserToken.class);

	private static final String STATIC_KEY_COMPONENT = "cpaHSK98lsjd9000";
	private static final int MAX_SAFE_CIPHER_KEY_LENGTH = 55;
	
	private static final String DELIMITER = "::";

	private static final String EQUATOR = "=";	
	
	private static final String USERID_TOKEN_NAME   = "uid";
	private static final String USERNAME_TOKEN_NAME = "uname";
	
	private Long   userid;
	private String username;
	

	public void setLoggedInUserid(Long userid) {
		this.userid = userid;
	}

	public Long getLoggedInUserid() {
		return this.userid;
	}
	
	public void setLoggedInUsername(String username) {
		this.username = username;
	}

	public String getLoggedInUsername() {
		return this.username;
	}	
	
	public static UserToken fromToken(String data) throws CPAException {

		UserToken token = new UserToken();
		try {

			String sKey   = getEncryptionKey();
			String result = CPATokenCipher.rawDecrypt(sKey, data);

			HashMap<String, String> hash = StringUtil.hash(result, DELIMITER);
			token.setLoggedInUserid(new Long((String) hash.get(USERID_TOKEN_NAME)));
			token.setLoggedInUsername((String) hash.get(USERNAME_TOKEN_NAME));

		} catch (Throwable e) {
			throw new SecurityException("Validation of the token in the request is failed. Please use a valid URL and try again.");
		}
		return token;
	}	
	
	public String getToToken() {

		String token = null;

		try {

			token = USERID_TOKEN_NAME + EQUATOR + this.userid + DELIMITER + 
					USERNAME_TOKEN_NAME + EQUATOR + this.userid + DELIMITER;

			String sKey = getEncryptionKey();
			token = CPATokenCipher.rawEncrypt(sKey, token);
		} catch (Throwable e) {
			logger.error("CPAToken.getToToken encountered an issue while encrypting the admin token data." + e);
		}
		return token;
	}	
	
	private static String getEncryptionKey() {

		UserContext uc = AppContextHolder.getUserContext();

		/*
		 * Take only part of ucid to reduce the overall size of the key, which
		 * is near the blowfish limit.
		 */
		String ucid = StringUtil.replaceSubstring(uc.getId(), "S_tomcat", "");

		/*
		 * Build encryption key for separate dynamic/static components. Truncate
		 * if too long.
		 */
		String encryptionKey = ucid + "_" + STATIC_KEY_COMPONENT;

		if (encryptionKey.length() > MAX_SAFE_CIPHER_KEY_LENGTH) {
			encryptionKey = encryptionKey.substring(0, MAX_SAFE_CIPHER_KEY_LENGTH - 1);
		}

		return encryptionKey;
	}	
}
