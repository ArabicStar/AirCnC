package po.member;

import utils.info.member.ContactInfo;

/**
 * po of contact<br>
 * setter only for ContactPoBuilder, so immutable to outer package<br>
 * 
 * @author ClevelandAlto
 *
 */
public class ContactPo extends ContactInfo {
	ContactPo setEmail(String email) {
		this.email = email;
		return this;
	}

	ContactPo setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
		return this;
	}

	ContactPo setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}
}
