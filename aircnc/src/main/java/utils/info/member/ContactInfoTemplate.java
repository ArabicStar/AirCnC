package utils.info.member;

import java.io.Serializable;

/**
 * Abstract of contact info object.<br>
 * Define fields contained in ContactInfo, and checker for them.<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class ContactInfoTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7539698669279951909L;

	/**
	 * Identifier, no need to set mannully
	 */
	protected int cid;

	/**
	 * email address string
	 */
	protected String email;

	/**
	 * mobile phone number string
	 */
	protected String mobilePhone;

	/**
	 * fixed phone number string
	 */
	protected String fixedPhone;

	/**
	 * buffered blank
	 */
	protected static final String BLANK = "";

	/**
	 * email address string pattern<br>
	 * <i>%username%</i> <b>@</b><i>%servername%</i> <b>. </b><i>%domain%</i>
	 */
	private static final String EMAIL_PATTERN = "\\w+@\\w+\\.\\w+";

	/**
	 * mobile phone number string pattern<br>
	 * <i>11 numbers between 0 and 9</i>
	 */
	private static final String MOBILE_PHONE_PATTERN = "[0-9]{11}";

	/**
	 * fixed phone number string pattern<br>
	 * <i>3 or 4 number between 0 and 9</i> - <i>8 numbers between 0- 9</i>
	 */
	private static final String FIXED_PHONE_PATTERN = "[0-9]{3,4}-[0-9]{8}";

	/**
	 * Check email string.<br>
	 * 
	 * @param email
	 *            email string<br>
	 * @return if given email string matches pattern<br>
	 */
	public static final boolean checkEmail(String email) {
		return email != null && email.matches(EMAIL_PATTERN);
	}

	/**
	 * Check mobile phone string.<br>
	 * 
	 * @param mobilePhone
	 *            mobile phone string<br>
	 * @return if given email string matches pattern<br>
	 */
	public static final boolean checkMobilePhone(String mobilePhone) {
		return mobilePhone != null && mobilePhone.matches(MOBILE_PHONE_PATTERN);
	}

	/**
	 * Check fixed phone string.<br>
	 * 
	 * @param fixedPhone
	 *            fixed phone number string<br>
	 * @return if given fixed phone number string matches pattern<br>
	 */
	public static final boolean checkFixedPhone(String fixedPhone) {
		return fixedPhone != null && fixedPhone.matches(FIXED_PHONE_PATTERN);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fixedPhone == null) ? 0 : fixedPhone.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactInfoTemplate other = (ContactInfoTemplate) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fixedPhone == null) {
			if (other.fixedPhone != null)
				return false;
		} else if (!fixedPhone.equals(other.fixedPhone))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		return true;
	}

}
