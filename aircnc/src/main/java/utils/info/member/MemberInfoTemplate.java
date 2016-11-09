package utils.info.member;

import java.time.LocalDate;

/**
 * Member information template<br>
 * Provide universal field for subclass, and static checker and formatter for
 * use<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class MemberInfoTemplate {
	protected enum Type {
		PERSONAL, BUSINESS
	}

	protected Type type;
	protected String id;
	protected String name;
	protected int credit;
	protected ContactInfo contact;
	protected LocalDate birthday;
	protected String enterprise;

	private static final int ID_BOUND = 100000000;
	private static final String ID_PATTERN = "[0-9]{8}";
	private static final String ID_FORMAT_STRING = "%08d";
	private static final int WRONG_ID_MARKER = -1;

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}

	public static final boolean checkUserName(String name) {
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
