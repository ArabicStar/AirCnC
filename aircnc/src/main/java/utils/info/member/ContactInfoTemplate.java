package utils.info.member;

public abstract class ContactInfoTemplate {
	protected String email;
	protected String mobilePhone;
	protected String fixedPhone;

	protected static final String BLANK = "";

	private static final String EMAIL_PATTERN = "\\w+@\\w+\\.\\w+";
	private static final String MOBILE_PHONE_PATTERN = "[0-9]{11}";
	private static final String FIXED_PHONE_PATTERN = "[0-9]{3,4}-[0-9]{8}";
	private static final String ID_PATTERN = "[0-9]{8}";

	public static final boolean checkEmail(String email) {
		return email != null && email.matches(EMAIL_PATTERN);
	}

	public static final boolean checkMobilePhone(String mobilePhone) {
		return mobilePhone != null && mobilePhone.matches(MOBILE_PHONE_PATTERN);
	}

	public static final boolean checkFixedPhone(String fixedPhone) {
		return fixedPhone != null && fixedPhone.matches(FIXED_PHONE_PATTERN);
	}

	public static final boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}
}
