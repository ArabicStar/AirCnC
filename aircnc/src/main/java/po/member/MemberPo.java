package po.member;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;

/**
 * Po for Member.<br>
 * The public setters is only for builder and hibernate. Please <b>NOT</b> used
 * in client code<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class MemberPo extends MemberInfo {
	protected int passwordHash;
	protected int numId = Integer.MIN_VALUE;

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

	public int getNumId() {
		return numId;
	}

	@Override
	public ContactPo getContact() {
		return (ContactPo) contact;
	}

	/**
	 * Set id of a integer.<br>
	 * Only for hibernate.<br>
	 * 
	 * @param numId
	 */
	public void setNumId(int numId) {
		this.numId = numId;
		this.id = formatID(numId);
	}

	/**
	 * Set id of a string.<br>
	 * Only for hibernate and builder.<br>
	 * 
	 * @param id
	 * @return this instance
	 */
	public MemberPo setId(String id) {
		this.id = id;
		this.numId = Integer.valueOf(id);
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
