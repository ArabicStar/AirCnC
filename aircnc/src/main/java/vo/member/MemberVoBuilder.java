package vo.member;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoBuilder;

public class MemberVoBuilder extends MemberInfoBuilder {
	private static final MemberVo INVALID_MEMBER_VO;
	static {
		INVALID_MEMBER_VO = new PersonalMemberVo();
		INVALID_MEMBER_VO.invalidate();
	}

	public static final MemberVo getInvalidInfo() {
		return INVALID_MEMBER_VO;
	}

	public MemberVoBuilder(String type) {
		super(type);
	}

	public MemberVoBuilder(MemberInfo info) {
		this(info.getType());
		if (!info.isValid())
			return;

		setID(info.getID()).setContactInfo(info.getContact()).setBirthday(info.getBirthday())
				.setEnterprise(info.getEnterprise());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}

	public MemberVoBuilder setName(String name) {
		if (checkUserName(name))
			this.name = name;
		return this;
	}

	@Override
	public MemberVoBuilder setID(String id) {
		super.setID(id);
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
