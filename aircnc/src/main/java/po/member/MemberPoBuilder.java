package po.member;

import static utils.exception.StaticExceptionFactory.builderNotReadyEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;

import java.time.LocalDate;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoBuilder;

/**
 * Builder or MemberPo<br>
 * 
 * @author ClevelandAlto
 *
 */
@SuppressWarnings("serial")
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
	public static final MemberPo invalidInfo() {
		return INVALID_MEMBER_PO;
	}

	public MemberPoBuilder(String type) {
		super(type);
	}

	public MemberPoBuilder(MemberInfo info) {
		super(info);

		if (info instanceof MemberPo)
			setPasswordHash(((MemberPo) info).getPasswordHash());
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
			this.name = name;
		else
			throw illegalArgEx("Member's name");

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
			throw builderNotReadyEx();

		if (type == Type.BUSINESS)
			return new EnterpriseMemberPo().setId(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setLevel(level).setContact(contact).setEnterprise(enterprise);
		else
			return new PersonalMemberPo().setId(id).setName(name).setPasswordHash(passwordHash).setCredit(credit)
					.setLevel(level).setContact(contact).setBirthday(birthday);
	}

	public static final void updatePo(final MemberPo from, MemberPo to) {
		if (from == null || to == null || from == to)
			return;

		if (!from.getId().equals(to.getId()) || !from.getType().equals(to.getType()))
			throw inconsistentStatusEx();

		to.setName(from.getName()).setPasswordHash(from.getPasswordHash()).setCredit(from.getCredit())
				.setBirthday(from.getBirthday()).setEnterprise(from.getEnterprise());
		ContactPoBuilder.updatePo(from.getContact(), to.getContact());
	}
}
