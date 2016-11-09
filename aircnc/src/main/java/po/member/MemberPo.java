package po.member;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;

/**
 * po for Member<br>
 * The public setters is only for builder and hibernate, should not be used
 * other condition<br>
 * 
 * @author ClevelandAlto
 *
 */
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

	public MemberPo setID(String id) {
		this.id = id;
		return this;
	}

	public MemberPo setName(String name) {
		this.name = name;
		return this;
	}

	public MemberPo setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	public MemberPo setContact(ContactInfo contact) {
		this.contact = contact;
		return this;
	}

	public MemberPo setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	public abstract MemberPo setBirthday(LocalDate birthday);

	public abstract MemberPo setEnterprise(String enterprise);
}
