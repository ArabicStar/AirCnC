package utils.crypto;

public class BytesCharsConverter {
	public static String bytes2HexString(byte[] src) {
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

	public static byte[] hexString2Bytes(String src) {
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

	public static byte char2Byte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}
