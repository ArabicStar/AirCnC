package utils.info.member;

import static utils.exception.StaticExceptionFactory.*;
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
@SuppressWarnings("serial")
public abstract class ContactInfoBuilder extends ContactInfoTemplate {
	/**
	 * Initialize a builder from a given ContactInfo instance, information will
	 * be kept.<br>
	 * 
	 * @param info
	 *            a ContactInfo instance
	 */
	protected ContactInfoBuilder(ContactInfo info) {
		this.setCid(info.getCid()).setEmail(info.getEmail()).setFixedPhone(info.getFixedPhone())
				.setMobilePhone(info.getMobilePhone());
	}

	/**
	 * Default initialization. All field is set blank.<br>
	 */
	protected ContactInfoBuilder() {
		cid = 0;
		this.setEmail(null).setFixedPhone(null).setMobilePhone(null);
	}

	private ContactInfoBuilder setCid(int cid) {
		if (cid < 0)
			return this;

		this.cid = cid;
		return this;
	}

	/**
	 * Set email.<br>
	 * 
	 * @param email
	 *            email address string<br>
	 * @return this instance<br>
	 */
	public ContactInfoBuilder setEmail(String email) {
		if (email == null || email.length() == 0) {
			this.email = BLANK;
			return this;
		}

		if (!checkEmail(email))
			throw illegalArgEx("email string", email);

		this.email = email;
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
		if (fixedPhone == null || fixedPhone.length() == 0) {
			this.fixedPhone = BLANK;
			return this;
		}

		if (!checkFixedPhone(fixedPhone))
			throw new IllegalArgumentException("ContactInfoBuilder.setFixedPhone - Invalid Fixed Phone Number String");

		this.fixedPhone = fixedPhone;
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
		if (mobilePhone == null || mobilePhone.length() == 0) {
			this.mobilePhone = BLANK;
			return this;
		}

		if (!checkMobilePhone(mobilePhone))
			throw new IllegalArgumentException(
					"ContactInfoBuilder.setMobilePhone - Invalid Mobile Phone Number String");

		this.mobilePhone = mobilePhone;
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
		return cid >= 0 && (email == "" || checkEmail(email)) && (fixedPhone == "" || checkFixedPhone(fixedPhone))
				&& (mobilePhone == "" || checkMobilePhone(mobilePhone));
	}

	/**
	 * Build a ContactInfo instance. <br>
	 * If not ready, <b>IllgealStateException</b> will be thrown. <br>
	 * 
	 * @return ContactInfo instance built <br>
	 * @throws IllegalStateException
	 *             not ready
	 */
	public abstract ContactInfo getContactInfo();
}
