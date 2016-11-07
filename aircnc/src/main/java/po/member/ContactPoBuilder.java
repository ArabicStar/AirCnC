package po.member;

import utils.info.member.ContactInfo;
import utils.info.member.ContactInfoBuilder;

/**
 * Builder for ContactPo<br>
 * 
 * @author ClevelandAlto
 *
 */
public class ContactPoBuilder extends ContactInfoBuilder {
	@Override
	public ContactInfo getContactInfo() {
		if (isReady())
			return new ContactPo().setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);

		return new ContactPoBuilder().getContactInfo();
	}
}
