package com.cpacademy.core.cpa.util;

import java.security.Security;

import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.misc.HexDumpEncoder;

public class CPACipher {

	public static final String BLOWFISH_ECB_MODE = "ECB";
	public static final String BLOWFISH_CBC_MODE = "CBC";
	public static final String BLOWFISH_DEFAULT_MODE = BLOWFISH_ECB_MODE;

	private SecretKeySpec _skeySpec = null;
	private Cipher _encryptCipher = null;
	private Cipher _decryptCipher = null;
	private boolean _usePadding = false;
	private String _mode = BLOWFISH_DEFAULT_MODE;

	// Various Secret Keys Values
	public static final byte[] MSEC_USER_PASSWORD_SECRET_KEY_TEXT = { (byte) '0', (byte) '1', (byte) '2', (byte) '4', (byte) '5' };

	// "Dynamically" install the "SunJCE" JCE reference provider
	static {

		// Verify that the SunJCE provider is loaded (must be specified in java.security)
		if (Security.getProvider("SunJCE") == null) {

			Provider sunJce = new com.sun.crypto.provider.SunJCE();
			Security.addProvider(sunJce);
		}
	}

	/** Private constructor call by getInstance(). */
	private CPACipher(byte[] keyText, boolean usePadding) throws Exception {
		this(keyText, usePadding, BLOWFISH_DEFAULT_MODE);
	}

	/** Private constructor call by getInstance(). */
	private CPACipher(byte[] keyText, boolean usePadding, String mode) throws Exception {
		this._usePadding = usePadding;
		this._skeySpec = new SecretKeySpec(keyText, "Blowfish");
		this._mode = mode;
	}

	/** Helper method to get encryption cipher. */
	private Cipher getEncryptCipher() throws Exception {
		if (this._encryptCipher == null) {
			// only two types of padding support by this class currently.
			String paddingType = (this._usePadding ? "PKCS5Padding" : "NoPadding");

			// Create/initialize cipher
			this._encryptCipher = Cipher.getInstance("Blowfish/" + this._mode + "/" + paddingType);
			this._encryptCipher.init(Cipher.ENCRYPT_MODE, this._skeySpec);
		}
		return this._encryptCipher;
	}

	/** Helper method to get decryption cipher. */
	private Cipher getDecryptCipher() throws Exception {
		if (this._decryptCipher == null) {
			// only two types of padding support by this class currently.
			String paddingType = (this._usePadding ? "PKCS5Padding" : "NoPadding");

			// Create/initialize cipher
			this._decryptCipher = Cipher.getInstance("Blowfish/" + this._mode + "/" + paddingType);
			this._decryptCipher.init(Cipher.DECRYPT_MODE, this._skeySpec);
		}
		return this._decryptCipher;
	}

	/**
	 * Constructs a TDC cipher based on a specific secret key.
	 * 
	 * @param byte[] keyText - The secret key for cipher to use.
	 * @param boolean usePadding - Flag that controls whether to use padding or not.
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance(byte[] keyText, boolean usePadding, String mode) throws Exception {
		return new CPACipher(keyText, usePadding, mode);
	}

	/**
	 * Constructs a TDC cipher based on a specific secret key.
	 * 
	 * @param byte[] keyText - The secret key for cipher to use.
	 * @param boolean usePadding - Flag that controls whether to use padding or not.
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance(byte[] keyText, boolean usePadding) throws Exception {
		return new CPACipher(keyText, usePadding);
	}

	/**
	 * Constructs a TDC cipher based on the default secret key (i.e., same one used by TAS for Blowfish encryption) and no padding.
	 * 
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance() throws Exception {
		// create a TDC specifing NOT to use padding
		return getInstance(false /* no padding */);
	}

	/**
	 * Constructs a TDC cipher based on the default secret key (i.e., same one used by TAS for Blowfish encryption) and specified padding.
	 * 
	 * @param boolean usePadding - Flag that controls whether to use padding or not.
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance(boolean usePadding) throws Exception {
		// create a TDC specifing the default blowfish encryption mode (ECB).
		return getInstance(usePadding, BLOWFISH_DEFAULT_MODE);
	}

	/**
	 * Constructs a TDC cipher based on the default secret key (i.e., same one used by TAS for Blowfish encryption) and no padding.
	 * 
	 * @param blowfishMode
	 *            (ECB or CBC)
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance(String blowfishMode) throws Exception {
		return getInstance(false /* no padding */, blowfishMode);
	}

	/**
	 * Constructs a TDC cipher based on the default secret key (i.e., same one used by TAS for Blowfish encryption) and no padding.
	 * 
	 * @param boolean usePadding - Flag that controls whether to use padding or not.
	 * @param blowfishMode
	 *            (ECB or CBC)
	 * @return TdcCipher The TDC cipher object.
	 * @exception Exception
	 */
	public static CPACipher getInstance(boolean usePadding, String blowfishMode) throws Exception {

		// create the default secret TAS key in non-obvious way
		final byte[] secretKeyText = { (byte) '1', (byte) '3', (byte) '7', (byte) '5', (byte) '7' };

		// create a TDC cipher using the default TAS key and
		// specify NOT to use padding
		return getInstance(secretKeyText, usePadding, blowfishMode);
	}

	/**
	 * Encrypts the portion of the clear text message starting at index: clearTextOffset, for a length of clearTextLength, and returns the resulting byte buffer.
	 * 
	 * @param final byte [] Input: The clear (unencrypted) message.
	 * @param int The index offset into the clear message to begin encryption.
	 * @param int The length of the clear message to encrypt, starting at clearTextOffset. An exception will be throw if this value is not an even multiple of 8.
	 * @return byte[] A new byte buffer containing the encrypted message.
	 * @exception Exception
	 */
	public byte[] encrypt(final byte[] clearText, int clearTextOffset, int clearTextLength) throws Exception {

		// validate parameters, throw exception if any one not valid
		if (clearTextOffset < 0 || (clearTextOffset + clearTextLength) > clearText.length) {
			throw new Exception("Invalid offset or length");
		} else if (!_usePadding && (clearTextLength % 8) != 0) {
			throw new Exception("length(" + clearTextLength + ") not a multiple of 8");
		}

		return this.getEncryptCipher().doFinal(clearText, clearTextOffset, clearTextLength);

	} // end encrypt()1

	/**
	 * Encrypts the portion of the clear text message starting at index: clearTextOffset, for a length of clearTextLength, and returns the resulting byte buffer.
	 * 
	 * @param final byte [] Input: The clear (unencrypted) message.
	 * @param int The index offset into the clear message to begin encryption.
	 * @param int The length of the clear message to encrypt, starting at clearTextOffset. An exception will be throw if this value is not an even multiple of 8.
	 * @return void byte buffer containing the to be encrypted message is passed in.
	 * @exception Exception
	 */
	public void encrypt(final byte[] clearText, int clearTextOffset, int clearTextLength, byte[] encryptText, int encryptTextOffset) throws Exception {

		// validate parameters, throw exception if any one not valid
		if (clearTextOffset < 0 || (clearTextOffset + clearTextLength) > clearText.length || (encryptTextOffset + clearTextLength) > encryptText.length) {
			throw new Exception("Invalid offset or length");
		} else if (!_usePadding && (clearTextLength % 8) != 0) {
			throw new Exception("length(" + clearTextLength + ") not a multiple of 8");
		}

		if (this.getEncryptCipher().doFinal(clearText, clearTextOffset, clearTextLength, encryptText, encryptTextOffset) != clearTextLength) {
			throw new Exception("encryption output size mismatch");
		}

	} // end encrypt()2

	/**
	 * Encrypts a string and returns Base-64 encoded encrypted result.
	 */
	public String encrypt(String clearText) throws Exception {
		return this.encrypt(clearText, null);
	} // end encrypt()3

	/**
	 * Encrypts a string and returns Base-64 encoded encrypted result. Allows for an optional encoding string for the string-to-byte conversion.
	 */
	public String encrypt(String clearText, String encoding) throws Exception {
		byte[] input = null;
		if (encoding != null && encoding.trim().length() > 0) {
			input = clearText.getBytes(encoding);
		} else {
			input = clearText.getBytes();
		}
		byte[] encryptedBytes = encrypt(input, 0, input.length);

		// Base-64 encode the encrypted byte array to make displayable
		// and return resultant string.
		BASE64Encoder base64Encoder = new BASE64Encoder();
		// HexDumpEncoder hde = new HexDumpEncoder();
		return base64Encoder.encode(encryptedBytes);

	} // end encrypt()4

	/**
	 * Decrypts portion of a byte arrary.
	 */
	public byte[] decrypt(final byte[] encryptText, int encryptTextOffset, int encryptTextLength) throws Exception {

		// validate parameters, throw exception if any one not valid
		if (encryptTextOffset < 0 || (encryptTextOffset + encryptTextLength) > encryptText.length) {
			throw new Exception("Invalid offset or length");
		} else if (!_usePadding && (encryptTextLength % 8) != 0) {
			throw new Exception("length(" + encryptTextLength + ") not a multiple of 8");
		}

		return this.getDecryptCipher().doFinal(encryptText, encryptTextOffset, encryptTextLength);

	} // end decrypt()1

	/**
	 * Decrypts portion of a byte arrary.
	 */
	public void decrypt(final byte[] encryptText, int encryptTextOffset, int encryptTextLength, byte[] clearText, int clearTextOffset) throws Exception {

		// validate parameters, throw exception if any one not valid
		if (encryptTextOffset < 0 || (encryptTextOffset + encryptTextLength) > encryptText.length || (clearTextOffset + encryptTextLength) > clearText.length) {
			throw new Exception("Invalid offset or length");
		} else if (!_usePadding && (encryptTextLength % 8) != 0) {
			throw new Exception("length(" + encryptTextLength + ") not a multiple of 8");
		}

		if (this.getDecryptCipher().doFinal(encryptText, encryptTextOffset, encryptTextLength, clearText, clearTextOffset) != encryptTextLength) {
			throw new Exception("decryption output size mismatch");
		}

	} // end decrypt()2

	/**
	 * Decrypts a Base-64 encoded string and returns result as a string.
	 */
	public String decrypt(String base64EncodedEncrypted) throws Exception {
		return this.decrypt(base64EncodedEncrypted, null);
	} // end decrypt()3

	/**
	 * Decrypts a Base-64 encoded string and returns result as a string. Allows for an optional encoding string for the byte-to-string conversion.
	 */
	public String decrypt(String base64EncodedEncrypted, String encoding) throws Exception {
		// Base-64 decode the encrypted info back to a binary byte array
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] encryptedBytes = base64Decoder.decodeBuffer(base64EncodedEncrypted);

		// decrypt and return result back as a string
		String result = null;
		if (encoding != null && encoding.trim().length() > 0) {
			result = new String(decrypt(encryptedBytes, 0, encryptedBytes.length), encoding);
		} else {
			result = new String(decrypt(encryptedBytes, 0, encryptedBytes.length));
		}
		return result;

	} // end decrypt()4

	/**
	 * Encrypts a string and returns HEX encoded encrypted result. Allows for an optional encoding string for the string-to-byte conversion.
	 */
	public String encryptByHexDumpEncoder(String clearText, String encoding) throws Exception {
		byte[] input = null;
		if (encoding != null && encoding.trim().length() > 0) {
			input = clearText.getBytes(encoding);
		} else {
			input = clearText.getBytes();
		}
		byte[] encryptedBytes = encrypt(input, 0, input.length);

		// hex encode the encrypted byte array to make displayable
		// and return resultant string.
		HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
		return hexDumpEncoder.encode(encryptedBytes);

	}
}
