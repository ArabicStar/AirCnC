package utils.crypto;

import static utils.crypto.BytesCharsConverter.bytes2HexString;
import static utils.crypto.BytesCharsConverter.hexString2Bytes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Helper for crypto.<br>
 * Use AES algorithm
 * 
 * @author highill
 * @see <a>http://www.iteye.com/topic/1122076/</a>
 *
 */
public abstract class Cryptor {
	public static final String VIPARA = "1269571569321021";
	public static final String password = "8665513896432496";

	/**
	 * 对字符串加密
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static final String encrypt(String str) {
		byte[] src = str.getBytes();
		byte[] en = null;
		try {
			en = doEncrypt(src);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return bytes2HexString(en);
	}

	private static final byte[] doEncrypt(byte[] src) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

		return cipher.doFinal(src);
	}

	/**
	 * 对字符串解密
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static final String decrypt(String hexString) {
		byte[] buff = hexString2Bytes(hexString);

		if (buff == null)
			throw new IllegalArgumentException("Cryptor.decrypt - Wrong hex string");

		byte[] en = null;
		try {
			en = doDecrypt(buff);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		if (en == null)
			return null;

		return new String(en);
	}

	private static byte[] doDecrypt(byte[] buff) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);

		return cipher.doFinal(buff);
	}

}
