package com.cpacademy.core.cpa.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpacademy.core.cpa.util.StringUtil;


public class UserContext implements Serializable {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(UserContext.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4029708234569785274L;

	/** The _the number. */
	private static long _theNumber = 1;

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant UNDER_SCORE. */
	private static final String UNDER_SCORE = "_";

	/** The Constant TICKETS_SERVER_NAME. */
	private static final String TICKETS_SERVER_NAME = "tickets.serverName";

	/** The Constant DELIM. */
	private static final String DELIM = "|";

	/** Constants for encoding UserContext data to avoid URL encoding problems. */
	public static final String QUESTION_MARK_ENCODED_LITERAL = "_.._";

	/** The Constant AMPERSAND_ENCODED_LITERAL. */
	public static final String AMPERSAND_ENCODED_LITERAL = "_--_";

	/** The Constant EQUALS_SIGN_ENCODED_LITERAL. */
	public static final String EQUALS_SIGN_ENCODED_LITERAL = "_,,_";

	/** The Constant SEMICOLON_ENCODED_LITERAL. */
	public static final String SEMICOLON_ENCODED_LITERAL = "_**_";

	/** The Constant SLASH_ENCODED_LITERAL. */
	public static final String SLASH_ENCODED_LITERAL = "-..-";

	/** The Constant COLON_ENCODED_LITERAL. */
	public static final String COLON_ENCODED_LITERAL = "-__-";

	/** The Constant AT_SIGN_ENCODED_LITERAL. */
	public static final String AT_SIGN_ENCODED_LITERAL = "-,,-";

	/** The Constant QUESTION. */
	private static final String QUESTION = "?";

	/** The Constant AMPHERSAND. */
	private static final String AMPHERSAND = "&";

	/** The Constant EQUAL. */
	private static final String EQUAL = "=";

	/** The Constant SEMI_COLON. */
	private static final String SEMI_COLON = ";";

	/** The Constant SLASH. */
	private static final String SLASH = "/";

	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The Constant AT. */
	private static final String AT = "@";

	/** The Constant TO_STRING_ID. */
	private static final String TO_STRING_ID = "id=";

	/** The Constant TO_STRING_CAT. */
	private static final String TO_STRING_CAT = ", category=";

	/** The Constant TO_STRING_IORIGIN. */
	private static final String TO_STRING_IORIGIN = ", internalOrigin=";

	/** The Constant TO_STRING_EORIGIN. */
	private static final String TO_STRING_EORIGIN = ", externalOrigin=";

	/** The Constant TO_STRING_NAME. */
	private static final String TO_STRING_NAME = ", name=";

	/** The Constant TO_STRING_LANGCD. */
	private static final String TO_STRING_LANGCD = ", languageCode=";

	/** The Constant TO_STRING_CNTRYCD. */
	private static final String TO_STRING_CNTRYCD = ", countryCode=";

	/** The Constant TO_STRING_SACD. */
	private static final String TO_STRING_SACD = ", subAgencyCode=";

	/** The Constant TO_STRING_RURL. */
	private static final String TO_STRING_RURL = ", refererURL=";

	/** The Constant TO_STRING_CURL. */
	private static final String TO_STRING_CURL = ", currentURL=";

	/** The id. */
	private String id = null;

	/** The category. */
	private String category = null;

	/** The internal origin. */
	private String internalOrigin = null;

	/** The external origin. */
	private String externalOrigin = null;

	/** The name. */
	private String name = null;

	/** The language code. */
	private String languageCode = null;

	/** The country code. */
	private String countryCode = null;

	/** The sub agency code. */
	private String subAgencyCode = null;

	/** The referer url. */
	private String refererURL = null;

	/** The current url. */
	private String currentURL = null;
	
	private String currentDomain = null;

	/** The admin token data. */
	private String adminTokenData = null;

	// A user category that represents web users.
	/** The Constant SERVLET_USER. */
	public static final String SERVLET_USER = "S";

	/** The Constant MAINT_USER. */
	public static final String MAINT_USER = "M";

	/** The Constant EJB_USER. */
	public static final String EJB_USER = "E";

	/** The Constant TAG_USER. */
	public static final String TAG_USER = "T";

	/** The Constant XML_USER. */
	public static final String XML_USER = "X";

	/** The Constant JSP_USER. */
	public static final String JSP_USER = "J";

	/** The _parm hash. */
	private HashMap<String, Object> _parmHash = new HashMap<String, Object>();

	/**
	 * The sequence number generator. It should be synchronized because a variable of long type is treated as 2 32 bits variables by java thread operations.
	 * 
	 * @return the long
	 */
	private static synchronized long _getNext() {
		return _theNumber++;
	}

	// Default constructor.
	/**
	 * Instantiates a new user context.
	 */
	public UserContext() {
	}

	/**
	 * Instantiates a new user context.
	 * 
	 * @param category
	 *            - the category of USERS (CONTROLLER_USER, EJB_USER, TAG_USER, JSP_USER)
	 * @param externalOrigin
	 *            - It tells us how a user entered our system (ie, the user's domain/server/process name. For a web user, the only entry point is the first purchase page servlet. Only this servlet can
	 *            tell us how you came in, so only it can define the external origin. * @param name
	 * @param name
	 *            the name
	 * @param languageCode
	 *            - the two-letter ISO-639 code: http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
	 * @param countryCode
	 *            - uppercase two-letter ISO-3166 code: http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	 * @param subAgencyCode
	 *            - the resolved SubAgency Code
	 * @param refererURL
	 *            - the Referer URL
	 */
	public UserContext(String category, String externalOrigin, String name, String languageCode, String countryCode, String subAgencyCode, String refererURL) {

		this.category = category;
		this.externalOrigin = externalOrigin;
		this.name = name;
		this.languageCode = (languageCode == null || languageCode.length() == 0 ? "en" : languageCode);
		this.countryCode = (countryCode == null || countryCode.length() == 0 ? "US" : countryCode);
		this.subAgencyCode = subAgencyCode;
		this.refererURL = refererURL;

		this.internalOrigin = TICKETS_SERVER_NAME;

		// Issue warning of the internal origin is null.
		if (this.internalOrigin == null)
			logger.warn("The internal origin returned is null.");

		// Default subAgencyCode to TDC and issue warning if it is null.
		if (this.subAgencyCode == null || this.subAgencyCode.length() == 0) {
			this.subAgencyCode = "TDC";
			logger.warn("The SubAgencyCode was null. Defaulting SubAgencyCode to TDC (Tickets.com).");
		}

		// Building the unique id.
		this.id = this.category + UNDER_SCORE + this.internalOrigin + UNDER_SCORE + System.currentTimeMillis() + UNDER_SCORE + UserContext._getNext();

	}

	// This constructor defaults refererURL to null.
	/**
	 * Instantiates a new user context.
	 * 
	 * @param category
	 *            the category
	 * @param externalOrigin
	 *            the external origin
	 * @param name
	 *            the name
	 * @param languageCode
	 *            the language code
	 * @param countryCode
	 *            the country code
	 * @param subAgencyCode
	 *            the sub agency code
	 */
	public UserContext(String category, String externalOrigin, String name, String languageCode, String countryCode, String subAgencyCode) {
		this(category, externalOrigin, name, languageCode, countryCode, subAgencyCode, null);
	}

	// This constructor will default category to "S" (SERVLET_USER), language code to "en" (English), and country code to "US" (United States).
	/**
	 * Instantiates a new user context.
	 * 
	 * @param externalOrigin
	 *            the external origin
	 * @param name
	 *            the name
	 * @param subAgencyCode
	 *            the sub agency code
	 */
	public UserContext(String externalOrigin, String name, String subAgencyCode) {
		this(UserContext.SERVLET_USER, externalOrigin, name, "en", "US", subAgencyCode, null);
	}

	// A private constructor used by fromCookie() method to re-construct an UserContext object from the cookie string.
	/**
	 * Instantiates a new user context.
	 * 
	 * @param id
	 *            the id
	 * @param category
	 *            the category
	 * @param internalOrigin
	 *            the internal origin
	 * @param externalOrigin
	 *            the external origin
	 * @param languageCode
	 *            the language code
	 * @param countryCode
	 *            the country code
	 * @param name
	 *            the name
	 * @param subAgencyCode
	 *            the sub agency code
	 * @param refererURL
	 *            the referer url
	 */
	private UserContext(String id, String category, String internalOrigin, String externalOrigin, String languageCode, String countryCode, String name, String subAgencyCode, String refererURL) {

		this.id = id;
		this.category = category;
		this.internalOrigin = internalOrigin;
		this.externalOrigin = externalOrigin;
		this.languageCode = languageCode;
		this.countryCode = countryCode;
		this.name = name;
		this.subAgencyCode = subAgencyCode;
		this.refererURL = refererURL;

		// Replace original origin/server with this requests origin/server. This will help with debugging (i.e., translating a sunappX error back to the sunwebX for this request). */
		this.internalOrigin = TICKETS_SERVER_NAME;
	}

	/**
	 * Encodes a UserContext token, replacing characters that can cause problems within URLs.
	 * 
	 * @param token
	 *            - the token string to use to recreate the UserContext.
	 * @param uc
	 *            - it will be ignored, but is present to match the signature expected for fromToken()
	 * @return - a decoded <code>String</code> representation of the UserContext token.
	 */
	public static String encodeToken(String token, UserContext uc) {

		// Encode common characters that can mess up querystring parameter parsing. See http://www.ietf.org/rfc/rfc1738.txt for more information.
		token = StringUtil.replaceSubstring(token, QUESTION, QUESTION_MARK_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, AMPHERSAND, AMPERSAND_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, EQUAL, EQUALS_SIGN_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, SEMI_COLON, SEMICOLON_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, SLASH, SLASH_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, COLON, COLON_ENCODED_LITERAL);
		token = StringUtil.replaceSubstring(token, AT, AT_SIGN_ENCODED_LITERAL);
		return token;
	}

	/**
	 * Decodes a UserContext token, replacing characters that were encoded for URL encoding reasons.
	 * 
	 * @param token
	 *            - the token string to use to recreate the UserContext
	 * @param uc
	 *            - this will be ignored, but is present to match the signature expected for fromToken().
	 * @return a decoded String representation of the UserContext token.
	 */
	public static String decodeToken(String token, UserContext uc) {

		// Decode common characters that can mess up querystring parameter parsing. See http://www.ietf.org/rfc/rfc1738.txt for more information.
		token = StringUtil.replaceSubstring(token, QUESTION_MARK_ENCODED_LITERAL, QUESTION);
		token = StringUtil.replaceSubstring(token, AMPERSAND_ENCODED_LITERAL, AMPHERSAND);
		token = StringUtil.replaceSubstring(token, EQUALS_SIGN_ENCODED_LITERAL, EQUAL);
		token = StringUtil.replaceSubstring(token, SEMICOLON_ENCODED_LITERAL, SEMI_COLON);
		token = StringUtil.replaceSubstring(token, SLASH_ENCODED_LITERAL, SLASH);
		token = StringUtil.replaceSubstring(token, COLON_ENCODED_LITERAL, COLON);
		token = StringUtil.replaceSubstring(token, AT_SIGN_ENCODED_LITERAL, AT);

		return token;
	}

	/**
	 * Returns a UserContext object reconstructed from a token string.
	 * 
	 * @param token
	 *            - a String giving the token string to use to recreate the UserContext.
	 * @param uc
	 *            the uc
	 * @return the UserContext recreated from the token string
	 */
	public static UserContext fromToken(String token, UserContext uc) {

		UserContext userContext = null;

		if (token != null) {

			// Decode the token, which was made HTTP 'get' safe.
			token = UserContext.decodeToken(token, uc);

			// Define array that will hold all 9 parameters to construct the UserContext.
			// Sequentially these parms are: id, category, internalOrigin, externalOrigin, languageCode, countryCode, name, subAgencyCode & referrer.
			String[] params = new String[9];
			List<String> tokens = StringUtil.listTokenize(token, DELIM);
			int size = tokens.size();
			int paramCount = 0;
			for (String param : tokens) {
				params[paramCount] = param;
				paramCount++;
			}

			// If we only have a single token, return null as data is completely corrupted. SubAgencyCode is no longer defaulted. TdcServlet resolves it again if necessary.
			if (size > 1) {
				userContext = new UserContext(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8]);
			}
		}
		return userContext;
	}

	// Returns a string representation of this user context, in the form of name=value pairs seperated by commas.
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String cookie = TO_STRING_ID + this.id + TO_STRING_CAT + this.category + TO_STRING_IORIGIN + this.internalOrigin + TO_STRING_EORIGIN + this.externalOrigin + TO_STRING_NAME + this.name + TO_STRING_LANGCD + this.languageCode + TO_STRING_CNTRYCD + this.countryCode + TO_STRING_SACD + this.subAgencyCode + TO_STRING_RURL + this.refererURL + TO_STRING_CURL + this.currentURL;

		return cookie;
	}

	/**
	 * The _id consists of 4 parts: A character prefix - C for Controller, E for EJB, T for TAG The formal name of the server process through which the user entered. Time stamp, in milliseconds from
	 * Jan. 1st, 1970. A sequence number generated internally
	 * 
	 * @return - the ID of this UserContext
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the category.
	 * 
	 * @return - the category of this UserContext (C for Controller user, E for EJB user, T for TAG user.)
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * It tells what server process brought this user's request into existence. The server that served the original request for this server is encoded in the UserContext ID.
	 * 
	 * @return - the internal origin of this UserContext
	 */
	public String getInternalOrigin() {
		return this.internalOrigin;
	}

	/**
	 * It tells where the user comes from (eg, the user's domain).
	 * 
	 * @return - the external origin of this UserContext
	 */
	public String getExternalOrigin() {
		return this.externalOrigin;
	}

	/**
	 * The user's formal name, if available. It can be null.
	 * 
	 * @return - the name of the UserContext
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This gives the lowercase two-letter ISO-639 code.
	 * 
	 * @return - the language code of this UserContext
	 * @see http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
	 */
	public String getLanguageCode() {
		return this.languageCode;
	}

	/**
	 * This gives the UpperCase two-letter ISO-3166 code (http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html)
	 * 
	 * @return the country code
	 * @see http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	 */
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * Gets the locale.
	 * 
	 * @return - the Locale of this UserContext
	 */
	public Locale getLocale() {
		return new Locale(this.languageCode, this.countryCode);
	}

	/**
	 * Gets the sub agency code.
	 * 
	 * @return - the SubAgencyCode of this UserContext
	 */
	public String getSubAgencyCode() {
		return this.subAgencyCode;
	}

	/**
	 * Gets the referer url.
	 * 
	 * @return - the current URL (protocol, domain, path & servlet) of this UserContext
	 */
	public String getRefererURL() {
		return this.refererURL;
	}

	/**
	 * Gets the current url.
	 * 
	 * @return - the current URL of this UserContext
	 */
	public String getCurrentURL() {
		return this.currentURL;
	}

	public String getCurrentDomain() {
		return this.currentDomain;
	}
	
	/**
	 * Setter for the UserContext ID.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter for the Category.
	 * 
	 * @param category
	 *            the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Setter for the internal origin.
	 * 
	 * @param internalOrigin
	 *            the new internal origin
	 */
	public void setInternalOrigin(String internalOrigin) {
		this.internalOrigin = internalOrigin;
	}

	/**
	 * Setter for the external Origin.
	 * 
	 * @param externalOrigin
	 *            the new external origin
	 */
	public void setExternalOrigin(String externalOrigin) {
		this.externalOrigin = externalOrigin;
	}

	/**
	 * Setter for the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for the Language Code.
	 * 
	 * @param languageCode
	 *            the new language code
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * Setter for the Country Code.
	 * 
	 * @param countryCode
	 *            the new country code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Setter for the SubAgency Code.
	 * 
	 * @param subAgencyCode
	 *            the new sub agency code
	 */
	public void setSubAgencyCode(String subAgencyCode) {
		this.subAgencyCode = subAgencyCode;
	}

	/**
	 * Setter for the Referrer URL.
	 * 
	 * @param refererURL
	 *            the new referer url
	 */
	public void setRefererURL(String refererURL) {
		this.refererURL = refererURL;
	}

	/**
	 * Setter for the Current URL.
	 * 
	 * @param currentURL
	 *            the new current url
	 */
	public void setCurrentURL(String currentURL) {
		this.currentURL = currentURL;
	}

	public void setCurrentDomain(String currentDomain) {
		this.currentDomain = currentDomain;
	}
	
	/**
	 * This method will return this entity's context parameter value associated with the passed text code. See the constants defined in the util.common.Constants class for known text type codes.
	 * ALWAYS USE THE CONSTANTS AS THE CODES THEMSELVES MAY CHANGE! If no text for the passed code is found, then the passed default value is returned.
	 * 
	 * @param textCode
	 *            the text code
	 * @return Object context parameter value associated with the passed code. If key/value no found, null is returned.
	 */
	public Object getParm(String textCode) {
		return this._parmHash.get(textCode);
	}

	/**
	 * This method will set this entity's context parameter value associated with the passed text code. See the constants defined in the util.common.Constants class for known text type codes. ALWAYS
	 * USE THE CONSTANTS AS THE CODES THEMSELVES MAY CHANGE!
	 * 
	 * @param textCode
	 *            the text code
	 * @param value
	 *            the value
	 * @return Object value of context parameter (if any) prior to it being set by this call.
	 */
	public Object setParm(String textCode, Object value) {
		Object rval = null;
		if (value == null) {
			rval = this._parmHash.remove(textCode);
		} else {
			rval = this._parmHash.put(textCode, value);
		}
		return rval;
	}



	/**
	 * Generates a token String that can be used to recreate the original UserContext.
	 * 
	 * @return the string
	 */
	public String toToken() {
		String rval = this.toTokenWithoutReferrer() + DELIM + (refererURL == null ? EMPTY_STRING : refererURL);
		rval = UserContext.encodeToken(rval, null);
		return rval;
	}

	/**
	 * To token without referrer.
	 * 
	 * @return - Generates a token String that can be used to recreate the original UserContext.
	 */
	public String toTokenWithoutReferrer() {
		String rval = id + DELIM + category + DELIM + internalOrigin + DELIM + externalOrigin + DELIM + languageCode + DELIM + countryCode + DELIM + name + DELIM + subAgencyCode;

		rval = UserContext.encodeToken(rval, null);
		return rval;
	}
}
