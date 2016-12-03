package utils.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Helper for crypto.<br>
 * Use AES algorithm
 * 
 * @author highill
 * @see <a>http://www.iteye.com/topic/1122076/</a>
 *
 */
public abstract class Cryptor {

	private static KeyGenerator keygen;
	private static SecretKey deskey;
	private static Cipher c;

	static {
		try {
			init();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	private static final void init() throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
		keygen = KeyGenerator.getInstance("AES");
		// 生成密钥
		deskey = keygen.generateKey();
		// 生成Cipher对象,指定其支持的DES算法
		c = Cipher.getInstance("AES");

	}

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
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return bytes2HexString(en);
	}

	private static final byte[] doEncrypt(byte[] src)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		c.init(Cipher.ENCRYPT_MODE, deskey);
		return c.doFinal(src);
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
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		if (en == null)
			return null;

		return new String(en);
	}

	private static byte[] doDecrypt(byte[] buff)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		c.init(Cipher.DECRYPT_MODE, deskey);
		return c.doFinal(buff);
	}

	private static String bytes2HexString(byte[] src) {
		if (src == null || src.length <= 0)
			return null;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
				sb.append(0);
			sb.append(hv);
		}
		return sb.toString();
	}

	private static byte[] hexString2Bytes(String src) {
		if (src == null || src.length() == 0)
			return null;

		src = src.toUpperCase();
		int len = src.length() / 2;
		char[] hexChars = src.toCharArray();
		byte[] bs = new byte[len];
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			bs[i] = (byte) (char2Byte(hexChars[pos]) << 4 | char2Byte(hexChars[pos + 1]));
		}
		return bs;
	}

	private static byte char2Byte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

}
