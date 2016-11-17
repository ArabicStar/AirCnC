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
	 * Id of the member who the contact info belongs to<br>
	 * Also, being the primary key of table <i>contact</i>
	 */
	private int memberID;

	/**
	 * @param memberID
	 *            the member id<br>
	 * @return this instance<br>
	 */
	public ContactPo setMemberID(int memberID) {
		this.memberID = memberID;
		return this;
	}

	/**
	 * @param email
	 *            the email address<br>
	 * @return this instance<br>
	 */
	public ContactPo setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @param fixedPhone
	 *            the fixed phone number<br>
	 * @return this instance<br>
	 */
	public ContactPo setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
		return this;
	}

	/**
	 * @param mobilePhone
	 *            the mobile phone number<br>
	 * @return this instance<br>
	 */
	public ContactPo setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public int getMemberID() {
		return memberID;
	}
}
