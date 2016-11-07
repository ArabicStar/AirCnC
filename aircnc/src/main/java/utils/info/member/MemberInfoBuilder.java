package utils.info.member;

import java.time.LocalDate;

public abstract class MemberInfoBuilder extends MemberInfoTemplate {
	protected MemberInfoBuilder() {
		credit = 0;
	}

	public MemberInfoBuilder(MemberInfo info) {
		this(info.getType());
		this.setID(info.getID()).setName(info.getName()).setContactInfo(info.getContact()).setCredit(info.getCredit())
				.setEnterprise(info.getEnterprise()).setBirthday(info.getBirthday());
	}

	public MemberInfoBuilder(String type) {
		if (type == null)
			throw new IllegalArgumentException();

		this.type = Type.valueOf(type);
		if (this.type == null)
			throw new IllegalArgumentException();
	}

	public MemberInfoBuilder setID(String id) {
		if (checkID(id))
			this.id = id;
		return this;
	}

	public abstract MemberInfoBuilder setName(String name);

	public MemberInfoBuilder setContactInfo(ContactInfo contact) {
		if (contact != null)
			this.contact = contact;
		return this;
	}

	public MemberInfoBuilder setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	public MemberInfoBuilder setEnterprise(String enterprise) {
		if (enterprise != null)
			this.enterprise = enterprise;
		return this;
	}

	public MemberInfoBuilder setBirthday(LocalDate birthday) {
		if (birthday != null)
			this.birthday = birthday;
		return this;
	}

	public boolean isReady() {
		return id != null && name != null && contact != null
				&& (type == Type.BUSINESS ? enterprise != null : birthday != null);
	}

	public abstract MemberInfo getMemberInfo();
}
