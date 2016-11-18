package utils.info.member;

/**
 * Abstract of contact info.<br>
 * Immutable object.<br>
 * P.S. Actually, ContactVo and ContactPo perform just the same<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class ContactInfo extends ContactInfoTemplate {

	protected ContactInfo() {
		email = BLANK;
		mobilePhone = BLANK;
		fixedPhone = BLANK;
	}

	public String getEmail() {
		return email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}
}
