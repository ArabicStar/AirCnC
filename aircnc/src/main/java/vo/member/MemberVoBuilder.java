package vo.member;

import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoBuilder;

public class MemberVoBuilder extends MemberInfoBuilder {
	private static final MemberInfo INVALID_MEMBER_VO;
	static {
		INVALID_MEMBER_VO = new PersonalMemberVo();
		INVALID_MEMBER_VO.invalidate();
	}

	public static final MemberInfo getInvalidInfo() {
		return INVALID_MEMBER_VO;
	}

	public MemberVoBuilder(String type) {
		super(type);
	}

	public MemberVoBuilder(MemberInfo info) {
		this(info.getType());
		if (!info.isValid())
			return;

		setID(info.getID()).setContactInfo(info.getContact()).setBirthday(birthday).setEnterprise(enterprise);
		String name = info.getName().replace("\\s*", "");
		setName(name);
	}

	public MemberVoBuilder setName(String name) {
		if (checkUserName(name))
			this.name = name;
		return this;
	}

	public MemberInfo getMemberVo() {
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
