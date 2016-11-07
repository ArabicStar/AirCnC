package po.member;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoBuilder;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

/**
 * Builder or MemberPo<br>
 * 
 * @author ClevelandAlto
 *
 */
public class MemberPoBuilder extends MemberInfoBuilder {
	private int passwordHash;

	private static final MemberPo INVALID_MEMBER_PO;
	static {
		INVALID_MEMBER_PO = new PersonalMemberPo();
		INVALID_MEMBER_PO.invalidate();
	}

	public static final MemberInfo getInvalidInfo() {
		return INVALID_MEMBER_PO;
	}

	public MemberPoBuilder(String type) {
		super(type);
	}

	public MemberPoBuilder(MemberInfo info) {
		this(info.getType());
		if (!info.isValid())
			return;

		setID(info.getID()).setContactInfo(info.getContact()).setBirthday(birthday).setEnterprise(enterprise);
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}

	@Override
	public MemberInfoBuilder setID(String id) {
		super.setID(id);
		return this;
	}

	@Override
	public MemberInfoBuilder setCredit(int credit) {
		super.setCredit(credit);
		return this;
	}

	public MemberPoBuilder setName(String name) {
		if (checkUserName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");

		return this;
	}

	@Override
	public MemberPoBuilder setBirthday(LocalDate birthday) {
		super.setBirthday(birthday);
		return this;
	}

	@Override
	public MemberPoBuilder setContactInfo(ContactInfo contact) {
		super.setContactInfo(contact);
		return this;
	}

	@Override
	public MemberPoBuilder setEnterprise(String enterprise) {
		super.setEnterprise(enterprise);
		return this;
	}

	public MemberPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	@Override
	public MemberPo getMemberInfo() {
		if (!isReady())
			return null;

		if (type == Type.BUSINESS)
			return new EnterpriseMemberPo().setID(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setContact(contact).setEnterprise(enterprise);
		else
			return new PersonalMemberPo().setID(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setContact(contact).setBirthday(birthday);
	}
}
