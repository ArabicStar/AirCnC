package vo.member;

import utils.info.member.ContactInfo;
import utils.info.member.EnterpriseMemberInfo;

public class EnterpriseMemberVo extends EnterpriseMemberInfo {

	@Override
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}

	EnterpriseMemberVo setID(String id) {
		this.id = id;
		return this;
	}

	EnterpriseMemberVo setName(String name) {
		this.name = name;
		return this;
	}

	EnterpriseMemberVo setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	EnterpriseMemberVo setContact(ContactInfo contact) {
		this.contact = contact;
		return this;
	}

	EnterpriseMemberVo setEnterprise(String enterprise) {
		this.enterprise = enterprise;
		return this;
	}
}
