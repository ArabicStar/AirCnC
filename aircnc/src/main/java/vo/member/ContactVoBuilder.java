package vo.member;

import utils.info.member.ContactInfo;
import utils.info.member.ContactInfoBuilder;

public class ContactVoBuilder extends ContactInfoBuilder {
	@Override
	protected ContactInfo getContactInfo() {
		if (isReady())
			return new ContactVo().setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);

		return new ContactVoBuilder().getContactInfo();
	}
}
