package po.member;

import utils.info.member.ContactInfo;

/**
 * Po of contact<br>
 * public setter only for builder and Hibernate, should not be used other
 * condition <br>
 * 
 * @author ClevelandAlto
 *
 */
public class ContactPo extends ContactInfo {
	private int serialID;
	private int memberID;

	public ContactPo setEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactPo setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
		return this;
	}

	public ContactPo setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public void setSerialID(int serialID) {
		this.serialID = serialID;
	}

	public int getMemberID() {
		return memberID;
	}

	public int getSerialID() {
		return serialID;
	}
}
