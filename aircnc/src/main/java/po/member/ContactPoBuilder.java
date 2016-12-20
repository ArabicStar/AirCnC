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
	public ContactPoBuilder() {
		super();
	}

	public ContactPoBuilder(ContactInfo contact) {
		super(contact);
	}

	public ContactPoBuilder setCid(int cid) {
		this.cid = cid;
		return this;
	}

	@Override
	public ContactInfo getContactInfo() {
		if (!isReady())
			throw new IllegalStateException("ContactPoBuilder - Lack Of Info");

		return new ContactPo().setCid(cid).setEmail(email).setFixedPhone(fixedPhone).setMobilePhone(mobilePhone);
	}

	public static final void updatePo(final ContactPo from, ContactPo to) {
		if (from == null || to == null || from == to)
			return;

		if (from.getCid() != to.getCid())
			throw new IllegalArgumentException("ContactrPoBuilder.updatePo - Different identifier");

		to.setEmail(from.getEmail()).setFixedPhone(from.getFixedPhone()).setMobilePhone(from.getMobilePhone());

	}
}
