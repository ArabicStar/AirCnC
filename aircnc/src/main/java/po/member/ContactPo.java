package po.member;

import static utils.crypto.Cryptor.*;

import utils.info.member.ContactInfo;

/**
 * Po of contact.<br>
 * Public setters are only for builder and Hibernate, please use
 * <i>ContactPoBuilder</i> to get instance<br>
 * 
 * @see po.member.ContactPoBuilder
 * 
 * @author ClevelandAlto
 *
 */
public class ContactPo extends ContactInfo {
	/**
	 * Identifier, no need to set mannully
	 */
	private int cid;

	/**
	 * @param cid<br>
	 *            the cid<br>
	 * @return this instance<br>
	 */

	public ContactPo setCid(int cid) {
		this.cid = cid;
		return this;
	}

	/**
	 * @param email<br>
	 *            the email address<br>
	 * @return this instance<br>
	 */
	public ContactPo setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @param fixedPhone<br>
	 *            the fixed phone number<br>
	 * @return this instance<br>
	 */
	public ContactPo setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
		return this;
	}

	/**
	 * @param mobilePhone<br>
	 *            the mobile phone number<br>
	 * @return this instance<br>
	 */
	public ContactPo setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	/**
	 * @return cid<br>
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * Get encrypted email string. Automatically encrypt {@code email} field.
	 * Just for hibernate. Make client requirement happy.<br>
	 * 
	 * @return encrypted email string.
	 */
	public String getEncryptEmail() {
		return encrypt(this.email);
	}

	/**
	 * Set encrypted email string. Automatically decrypt {@code encryptEmail}
	 * parameter and set to {@code email} field. Just for hibernate. Make client
	 * requirement happy.<br>
	 * 
	 * @param encryptEmail
	 *            encrpyted email string
	 */
	public void setEncryptEmail(String encryptEmail) {
		this.email = decrypt(encryptEmail);
	}

	/**
	 * Get encrypted fixed phone string. Automatically encrypt
	 * {@code fixedPhone} field. Just for hibernate. Make client requirement
	 * happy.<br>
	 * 
	 * @return encrypted fixed phone string.
	 */
	public String getEncryptFixedPhone() {
		return encrypt(this.fixedPhone);
	}

	/**
	 * Set encrypted fixed phone string. Automatically decrypt
	 * {@code encryptFixedPhone} parameter and set to {@code fixedPhone} field.
	 * Just for hibernate. Make client requirement happy.<br>
	 * 
	 * @param encryptFixedPhone
	 *            encrpyted fixed phone string
	 */
	public void setEncryptFixedPhone(String encryptFixedPhone) {
		this.email = decrypt(encryptFixedPhone);
	}

	/**
	 * Get encrypted mobile phone string. Automatically encrypt
	 * {@code mobilePhone} field. Just for hibernate. Make client requirement
	 * happy.<br>
	 * 
	 * @return encrypted mobile phone string.
	 */
	public String getEncryptMobilePhone() {
		return encrypt(this.mobilePhone);
	}

	/**
	 * Set encrypted mobile phone string. Automatically decrypt
	 * {@code encryptMobilePhone} parameter and set to {@code mobilePhone}
	 * field. Just for hibernate. Make client requirement happy.<br>
	 * 
	 * @param encryptMobilePhone
	 *            encrpyted mobile phone string
	 */
	public void setEncryptMobilePhone(String encryptMobilePhone) {
		this.email = decrypt(encryptMobilePhone);
	}
}