package po.member;

import utils.info.member.ContactInfo;
import static utils.crpyto.Cryptor.*;

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

	public String getEncryptEmail() {
		return encrypt(this.email);
	}

	public void setEncryptEmail(String encryptEmail) {
		this.email = decrypt(encryptEmail);
	}

	public String getEncryptFixedPhone() {
		return encrypt(this.fixedPhone);
	}

	public void setEncryptFixedPhone(String encryptFixedPhone) {
		this.email = decrypt(encryptFixedPhone);
	}

	public String getEncryptMobilePhone() {
		return encrypt(this.mobilePhone);
	}

	public void setEncryptMobilePhone(String encryptMobilePhone) {
		this.email = decrypt(encryptMobilePhone);
	}
}