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
	public ContactVoBuilder() {
		super();
	}

	public ContactVoBuilder(ContactInfo info) {
		super(info);
	}

	@Override
	public ContactInfo getContactInfo() {
		if (isReady())
			return new ContactVo().setCid(cid).setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);

		return new ContactVoBuilder().getContactInfo();
	}
}
