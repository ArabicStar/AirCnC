package po.member;

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
}
