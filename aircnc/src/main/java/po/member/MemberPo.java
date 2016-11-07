package po.member;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;

public abstract class MemberPo extends MemberInfo {
	protected int passwordHash;

	protected MemberPo(Type type) {
		super(type);
	}

	@Override
	public String getName() {
		if (isValid())
			return StringUtils.deleteWhitespace(this.name);
		return null;
	}

	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}

	MemberPo setID(String id) {
		this.id = id;
		return this;
	}

	MemberPo setName(String name) {
		this.name = name;
		return this;
	}

	MemberPo setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	MemberPo setContact(ContactInfo contact) {
		this.contact = contact;
		return this;
	}

	MemberPo setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	abstract MemberPo setBirthday(LocalDate birthday);

	abstract MemberPo setEnterprise(String enterprise);
}
