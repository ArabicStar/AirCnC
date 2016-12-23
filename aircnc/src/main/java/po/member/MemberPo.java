package po.member;

import static utils.crypto.Cryptor.decrypt;
import static utils.crypto.Cryptor.encrypt;

import java.time.LocalDate;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = -8984994455850219389L;
	protected int passwordHash;
	protected int numId = Integer.MIN_VALUE;

	protected MemberPo(Type type) {
		super(type);
	}

	@Override
	public String getName() {
		if (isValid())
			return name;
		return null;
	}

	/**
	 * Get password hash value.<br>
	 * 
	 * @return pwd hash value
	 */
	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}

	/**
	 * Get id number.<br>
	 * 
	 * @return id number
	 */
	public int getNumId() {
		if (isValid())
			return numId;
		return Integer.MIN_VALUE;
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

	public MemberPo setLevel(int level) {
		this.level = level;
		return this;
	}

	/**
	 * Get encrypted name string. Automatically encrypt {@code name} field. Just
	 * for hibernate. Make client requirement happy.<br>
	 * 
	 * @return encrypted name string.
	 */
	public String getEncryptName() {
		return encrypt(this.name);
	}

	/**
	 * Set encrypted name string. Automatically decrypt {@code encryptName}
	 * field. Just for hibernate. Make client requirement happy.<br>
	 * 
	 * @param encryptName
	 *            encrpyted name string
	 */
	public void setEncryptName(String encryptName) {
		this.name = decrypt(encryptName);
	}

}
