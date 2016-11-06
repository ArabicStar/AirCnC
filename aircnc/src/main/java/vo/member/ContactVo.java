package vo.member;

import utils.info.member.ContactInfo;

/**
 * vo of contact<br>
 * setter only for ContactVoBuilder, so immutable to outer package<br>
 * 
 * @author ClevelandAlto
 *
 */
public class ContactVo extends ContactInfo {
	ContactVo setEmail(String email) {
		this.email = email;
		return this;
	}

	ContactVo setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
		return this;
	}

	ContactVo setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}
}
