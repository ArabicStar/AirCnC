package utils.info.member;

/**
 * Abstract of contact info.<br>
 * Immutable object.<br>
 * All setter parameters will be assumed checked already, so checks are skipped.
 * <br>
 * 
 * @author ClevelandAlto
 * @see utils.info.member.ContactInfoBuilder
 */
public abstract class ContactInfo extends ContactInfoTemplate {

	/**
	 * Default initialization. <br>
	 * All fields are set blank. <br>
	 */
	protected ContactInfo() {
		email = BLANK;
		mobilePhone = BLANK;
		fixedPhone = BLANK;
	}

	/**
	 * Get email. <br>
	 * 
	 * @return email address string <br>
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get mobile phone. <br>
	 * 
	 * @return mobile phone number string <br>
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * Get fixed phone. <br>
	 * 
	 * @return fixed phone number string <br>
	 * 
	 */
	public String getFixedPhone() {
		return fixedPhone;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null || !(o instanceof ContactInfo))
			return false;

		ContactInfo i = (ContactInfo) o;
		return email.equals(i.getEmail()) && mobilePhone.equals(i.getMobilePhone())
				&& fixedPhone.equals(i.getFixedPhone());
	}

	@Override
	public int hashCode() {
		return email.hashCode() + fixedPhone.hashCode() + mobilePhone.hashCode();
	}
}
