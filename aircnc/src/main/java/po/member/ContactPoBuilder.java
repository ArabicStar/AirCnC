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
	private int cid;

	public ContactPoBuilder() {
		super();
	}

	public ContactPoBuilder(ContactInfo contact) {
		super(contact);

		if (contact instanceof ContactPo)
			this.cid = ((ContactPo) contact).getCid();
	}

	public ContactPoBuilder setCid(int cid) {
		this.cid = cid;
		return this;
	}

	public int getCid() {
		return cid;
	}

	@Override
	public ContactInfo getContactInfo() {
		if (isReady())
			return new ContactPo().setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);

		return new ContactPoBuilder().getContactInfo();
	}
}
