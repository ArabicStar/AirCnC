package vo.member;

import utils.info.member.ContactInfo;
import utils.info.member.ContactInfoBuilder;

/**
 * Builder for ContactVo<br>
 * 
 * @author ClevelandAlto
 *
 */
public class ContactVoBuilder extends ContactInfoBuilder {
	@Override
	public ContactInfo getContactInfo() {
		if (isReady())
			return new ContactVo().setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);

		return new ContactVoBuilder().getContactInfo();
	}
}
