package vo.member;

import java.time.LocalDate;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;

@SuppressWarnings("serial")
public abstract class MemberVo extends MemberInfo {

	protected MemberVo(Type type) {
		super(type);
	}

	@Override
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}

	MemberVo setID(String id) {
		this.id = id;
		return this;
	}

	MemberVo setName(String name) {
		this.name = name;
		return this;
	}

	MemberVo setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	MemberVo setContact(ContactInfo contact) {
		this.contact = contact;
		return this;
	}

	MemberVo setLevel(int level) {
		this.level = level;
		return this;
	}

	abstract MemberVo setBirthday(LocalDate birthday);

	abstract MemberVo setEnterprise(String enterprise);

}
