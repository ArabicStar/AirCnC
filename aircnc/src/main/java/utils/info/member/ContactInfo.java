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
	 * 
	 */
	private static final long serialVersionUID = -4820188152925515423L;

	/**
	 * Default initialization. <br>
	 * All fields are set blank. <br>
	 */
	protected ContactInfo() {
		cid = 0;
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

	public int getCid() {
		return cid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactInfo [cid=").append(cid).append(", email=").append(email).append(", mobilePhone=")
				.append(mobilePhone).append(", fixedPhone=").append(fixedPhone).append("]");
		return builder.toString();
	}

}
