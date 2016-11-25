package utils.info.member;

import java.time.LocalDate;

/**
 * Abstract of member info object.<br>
 * Define fields contained in MemberInfo, and checker for them.<br>
 * 
 * @author ClevelandAlto
 * 
 */
public abstract class MemberInfoTemplate {
	protected enum Type {
		PERSONAL, BUSINESS
	}

	/**
	 * Member type marker
	 */
	protected Type type;

	/**
	 * id string <br>
	 */
	protected String id;

	/**
	 * iname string <br>
	 */
	protected String name;

	/**
	 * credit <br>
	 */
	protected int credit;

	/**
	 * contact information <br>
	 */
	protected ContactInfo contact;

	/**
	 * birthday date, for personal member <br>
	 */
	protected LocalDate birthday;

	/**
	 * enterprise string, for business member <br>
	 */
	protected String enterprise;

	/**
	 * id string pattern <br>
	 * <i>8 numbers between 0 and 9</i>
	 */
	private static final String ID_PATTERN = "[0-9]{8}";

	/**
	 * bound of id integer
	 */
	private static final int ID_BOUND = 100000000;

	/**
	 * id string pattern format string, to format an integer to string <br>
	 * <i>8 numbers between 0 and 9</i>
	 */
	private static final String ID_FORMAT_STRING = "%08d";

	/**
	 * mark a wrong id integer <br>
	 */
	private static final int WRONG_ID_MARKER = -1;

	/**
	 * mark a wrong credit value <br>
	 */
	public static final int WRONG_CREDIT = Integer.MIN_VALUE;

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
