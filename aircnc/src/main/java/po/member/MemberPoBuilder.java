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
	private int passwordHash = Integer.MIN_VALUE;

	/**
	 * Invalid MemberPo instance.<br>
	 * Usually, used to mark invalid access and operation, etc.<br>
	 */
	private static final MemberPo INVALID_MEMBER_PO;
	static {
		INVALID_MEMBER_PO = new PersonalMemberPo();
		INVALID_MEMBER_PO.invalidate();
	}

	/**
	 * Get an invalid MemberPo instance.
	 * 
	 * @return Invalid MemberPo instance
	 */
	public static final MemberPo getInvalidInfo() {
		return INVALID_MEMBER_PO;
	}

	public MemberPoBuilder(String type) {
		super(type);
	}

	public MemberPoBuilder(MemberInfo info) {
		this(info.getType());
		if (!info.isValid())
			throw new IllegalArgumentException("Invalid MemberInfo Instance");

		setId(info.getId()).setContactInfo(info.getContact()).setBirthday(info.getBirthday())
				.setEnterprise(info.getEnterprise());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}

	@Override
	public MemberPoBuilder setId(String id) {
		super.setId(id);
		return this;
	}

	@Override
	public MemberPoBuilder setCredit(int credit) {
		super.setCredit(credit);
		return this;
	}

	public MemberPoBuilder setName(String name) {
		if (checkUserName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");
		else
			throw new IllegalArgumentException("Wrong name");

		return this;
	}

	@Override
	public MemberPoBuilder setBirthday(LocalDate birthday) {
		super.setBirthday(birthday);
		return this;
	}

	@Override
	public MemberPoBuilder setContactInfo(ContactInfo contact) {
		super.setContactInfo(new ContactPoBuilder(contact).getContactInfo());
		return this;
	}

	@Override
	public MemberPoBuilder setEnterprise(String enterprise) {
		super.setEnterprise(enterprise);
		return this;
	}

	/**
	 * @param passwordHash
	 * @return this instance
	 */
	public MemberPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	@Override
	public MemberPo getMemberInfo() {
		if (!isReady() && passwordHash != Integer.MIN_VALUE)
			throw new IllegalStateException("Lack Of Info");

		if (type == Type.BUSINESS)
			return new EnterpriseMemberPo().setId(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setContact(contact).setEnterprise(enterprise);
		else
			return new PersonalMemberPo().setId(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setContact(contact).setBirthday(birthday);
	}
}
