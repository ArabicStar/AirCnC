package utils.info.member;

/**
 * Abstract of builder for ContactInfo, assisting to assure immutable object.
 * <br>
 * <br>
 * <b>NOTICE:</b>
 * <ul>
 * <li>All fields is alternative. if not given, it will be blank.<br>
 * <li>If parameter of setter is empty, blank or null, the filed will be set
 * blank.<br>
 * <li>Else, parameters will be checked. The field will be set blank if invalid.
 * <br>
 * </ul>
 * 
 * @author ClevelandAlto
 * @see utils.info.member.ContactInfo
 * 
 */
public abstract class ContactInfoBuilder extends ContactInfoTemplate {
	/**
	 * Initialize a builder from a given ContactInfo instance, information will
	 * be kept.<br>
	 * 
	 * @param info
	 *            a ContactInfo instance
	 */
	protected ContactInfoBuilder(ContactInfo info) {
		this.setEmail(info.getEmail()).setFixedPhone(info.getFixedPhone()).setMobilePhone(info.getMobilePhone());
	}

	/**
	 * Default initialization. All field is set blank.<br>
	 */
	protected ContactInfoBuilder() {
		this.setEmail(null).setFixedPhone(null).setMobilePhone(null);
	}

	/**
	 * Set email.<br>
	 * 
	 * @param email
	 *            email address string<br>
	 * @return this instance<br>
	 */
	public ContactInfoBuilder setEmail(String email) {
		this.email = email == null ? BLANK : (checkEmail(email) ? email : BLANK);
		return this;
	}

	/**
	 * Set fixed phone.<br>
	 * 
	 * @param fixedPhone
	 *            fixed phone number string <br>
	 * @return this instance <br>
	 */
	public ContactInfoBuilder setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone == null ? BLANK : (checkFixedPhone(fixedPhone) ? fixedPhone : BLANK);
		return this;
	}

	/**
	 * Set mobile phone.<br>
	 * 
	 * @param mobilePhone
	 *            mobile phone number string <br>
	 * @return this instance <br>
	 */
	public ContactInfoBuilder setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? BLANK : (checkMobilePhone(mobilePhone) ? mobilePhone : BLANK);
		return this;
	}

	/**
	 * Check if builder can build a ContactInfo instance, that is, all fields
	 * are valid. <br>
	 * 
	 * @return if all fields are valid <br>
	 */
	public boolean isReady() {
		// blank contact info is allowed
		return (email == "" || checkEmail(email)) && (fixedPhone == "" || checkFixedPhone(fixedPhone))
				&& (mobilePhone == "" || checkMobilePhone(mobilePhone));
	}

	/**
	 * Build a ContactInfo instance. <br>
	 * If not ready, <b>IllgealStateException</b> will be thrown. <br>
	 * 
	 * @return ContactInfo instance built <br>
	 */
	public abstract ContactInfo getContactInfo();
}
