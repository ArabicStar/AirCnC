package utils.info.market;

/**
 * Market information template<br>
 * Provide universal field for subclass, and static checker and formatter for
 * use<br>
 *
 * @author ParanoiaSun
 *
 */

public class MarketInfoTemplate {

	protected String id;
	protected String name;

	private static final int ID_BOUND = 100000000;
	private static final String ID_PATTERN = "[0-9]{8}"; //正则表达式，表达8位0-9数字组成的id
	private static final String ID_FORMAT_STRING = "%08d"; //8位
	private static final int WRONG_ID_MARKER = -1;

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}

	public static final boolean checkUserName(String name) {
		//名字不为空，长度不为0，不包含空格等特殊字符
		return name != null && name.length() != 0 && !name.contains("\\s");
	}

	public static final int convertID2Num(String id) {
		if (!checkID(id))
			return WRONG_ID_MARKER;

		return Integer.valueOf(id);
	}

	public static final String formatID(int i) {
		if (i <= 0 && i >= ID_BOUND)
			return null;

		return String.format(ID_FORMAT_STRING, i);
	}
}
