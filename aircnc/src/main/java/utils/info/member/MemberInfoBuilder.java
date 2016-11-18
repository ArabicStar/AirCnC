package utils.info.member;

import java.time.LocalDate;

public abstract class MemberInfoBuilder extends MemberInfoTemplate {
	protected MemberInfoBuilder() {
		credit = 0;
	}

	public MemberInfoBuilder(MemberInfo info) {
		this(info.getType());
		this.setID(info.getId()).setName(info.getName()).setContactInfo(info.getContact()).setCredit(info.getCredit())
				.setEnterprise(info.getEnterprise()).setBirthday(info.getBirthday());
	}

	public MemberInfoBuilder(String type) {
		if (type == null)
			throw new IllegalArgumentException();

		this.type = Type.valueOf(type.toUpperCase());
		if (this.type == null)
			throw new IllegalArgumentException();
	}

	public MemberInfoBuilder setID(String id) {
		if (checkID(id))
			this.id = id;
		else
			throw new IllegalArgumentException("Wrong ID");
		return this;
	}

	public abstract MemberInfoBuilder setName(String name);

	public MemberInfoBuilder setContactInfo(ContactInfo contact) {
		if (contact != null)
			this.contact = contact;
		else
			throw new IllegalArgumentException("Null Contact info");
		return this;
	}

	public MemberInfoBuilder setCredit(int credit) {
		if (credit != Integer.MIN_VALUE)
			this.credit = credit;
		else
			throw new IllegalArgumentException("Wrong credit value");
		return this;
	}

	public MemberInfoBuilder setEnterprise(String enterprise) {
		if (enterprise != null)
			this.enterprise = enterprise;
		else
			throw new IllegalArgumentException("Null enterprise string");
		return this;
	}

	public MemberInfoBuilder setBirthday(LocalDate birthday) {
		if (birthday != null)
			this.birthday = birthday;
		else
			throw new IllegalArgumentException("Null birthday LocalDate");
		return this;
	}

	public boolean isReady() {
		// System.out.println(id + " " + name + " " + contact + " " + type + " "
		// + enterprise + " " + birthday);
		return id != null && name != null && contact != null
				&& (type == Type.BUSINESS ? enterprise != null : birthday != null);
	}

	public abstract MemberInfo getMemberInfo();
}
