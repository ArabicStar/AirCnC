package vo.member;

import java.time.LocalDate;

import utils.info.member.ContactInfo;
import utils.info.member.PersonalMemberInfo;

public class PersonalMemberVo extends PersonalMemberInfo {
	@Override
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}

	PersonalMemberVo setID(String id) {
		this.id = id;
		return this;
	}

	PersonalMemberVo setName(String name) {
		this.name = name;
		return this;
	}

	PersonalMemberVo setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	PersonalMemberVo setContact(ContactInfo contact) {
		this.contact = contact;
		return this;
	}

	PersonalMemberVo setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}
}
