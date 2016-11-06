package utils.info.member;

import java.time.LocalDate;

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

	private static final String ID_PATTERN = "[0-9]{8}";

	public boolean checkID(String s) {
		return s != null && s.matches(ID_PATTERN);
	}

	public boolean checkUserName(String name){
		return name!=null&& name.length()!=0&&!name.contains("\\s");
	}
}
