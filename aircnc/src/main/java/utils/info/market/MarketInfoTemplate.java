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
	
	protected static String BLANK = "";
	
	protected String id;
	protected String name;
	
	/**
	 * id string pattern <br>
	 * <i>8 numbers between 0 and 9</i>
	 */
	private static final String ID_PATTERN = "[0-9]{8}"; //正则表达式，表达8位0-9数字组成的id
	
	/**
	 * bound of id integer
	 */
	private static final int ID_BOUND = 100000000;
	
	/**
	 * id string pattern format string, to format an integer to string <br>
	 * <i>8 numbers between 0 and 9</i>
	 */
	private static final String ID_FORMAT_STRING = "%08d"; //8位
	
	/**
	 * mark a wrong id integer <br>
	 */
	private static final int WRONG_ID_MARKER = -1;
	
	/**
	 * Check id string. <br>
	 * 
	 * @param s
	 * @return if given string is a valid id string <br>
	 */
	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}
	
	/**
	 * Check name string. <br>
	 * 
	 * @param name
	 *            name string
	 * @return if given string is a valid name string <br>
	 */
	public static final boolean checkUserName(String name) {
		//名字不为空，长度不为0，不包含空格等特殊字符
		return name != null && name.length() != 0 && !name.contains("\\s");
	}
	
	/**
	 * Convert a id string to int <br>
	 * 
	 * @param id
	 *            id string <br>
	 * @return <b>WRONG_ID_MARKER</b> if invalid <br>
	 *         <b>value of id string</b> if valid <br>
	 */
	public static final int convertID2Num(String id) {
		if (!checkID(id))
			return WRONG_ID_MARKER;

		return Integer.valueOf(id);
	}
	
	/**
	 * Format a int to id string. <br>
	 * 
	 * @param i
	 *            an integer <br>
	 * @return <b>null</b> if invalid <br>
	 *         <b>formatted id string</b> of given integer by ID_PATTERN if
	 *         valid<br>
	 */
	public static final String formatID(int i) {
		if (i <= 0 && i >= ID_BOUND)
			return null;

		return String.format(ID_FORMAT_STRING, i);
	}
}
