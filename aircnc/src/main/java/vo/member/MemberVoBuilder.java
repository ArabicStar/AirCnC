package vo.member;

import java.time.LocalDate;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoBuilder;

@SuppressWarnings("serial")
public class MemberVoBuilder extends MemberInfoBuilder {
	private static final MemberVo INVALID_MEMBER_VO;
	static {
		INVALID_MEMBER_VO = new PersonalMemberVo();
		INVALID_MEMBER_VO.invalidate();
	}

	public static final MemberVo invalidInfo() {
		return INVALID_MEMBER_VO;
	}

	public MemberVoBuilder(String type) {
		super(type);
		contact = new ContactVo();
	}

	public MemberVoBuilder(MemberInfo info) {
		super(info);
	}

	public MemberVoBuilder setName(String name) {
		if (checkUserName(name))
			this.name = name;
		return this;
	}

	@Override
	public MemberVoBuilder setId(String id) {
		super.setId(id);
		return this;
	}

	@Override
	public MemberVoBuilder setCredit(int credit) {
		super.setCredit(credit);
		return this;
	}

	@Override
	public MemberVoBuilder setBirthday(LocalDate birthday) {
		super.setBirthday(birthday);
		return this;
	}

	@Override
	public MemberVoBuilder setContactInfo(ContactInfo contact) {
		super.setContactInfo(contact);
		return this;
	}

	@Override
	public MemberVoBuilder setEnterprise(String enterprise) {
		super.setEnterprise(enterprise);
		return this;
	}

	@Override
	public MemberVo getMemberInfo() {
		if (!isReady())
			return null;

		if (type == Type.BUSINESS)
			return new EnterpriseMemberVo().setID(id).setName(name).setCredit(credit).setContact(contact)
					.setEnterprise(enterprise);
		else
			return new PersonalMemberVo().setID(id).setName(name).setCredit(credit).setContact(contact)
					.setBirthday(birthday);
	}
}
