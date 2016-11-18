package utils.info.member;

import java.time.LocalDate;

/**
 * Abstract of member info<br>
 * to implement immutable object, a;ll setters are added in specific subclass
 * 
 * @author ClevelandAlto
 *
 */
public abstract class MemberInfo extends MemberInfoTemplate {

	protected boolean isValid;

	private static final String BLANK = "";

	protected MemberInfo(Type type) {
		this.type = type;
		id = BLANK;
		name = BLANK;
		credit = 0;
		contact = null;
		birthday = null;
		enterprise = null;
		isValid = true;
	}

	public String getType() {
		return type.name().toLowerCase();
	}

	public String getId() {
		if (isValid())
			return id;
		return null;
	}

	public abstract String getName();

	public int getCredit() {
		if (isValid())
			return credit;
		return Integer.MIN_VALUE;
	}

	public ContactInfo getContact() {
		if (isValid())
			return contact;
		return null;
	}

	public abstract LocalDate getBirthday();

	public abstract String getEnterprise();

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}
}
