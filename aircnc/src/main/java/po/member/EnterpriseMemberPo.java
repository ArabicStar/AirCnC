package po.member;

import static utils.crypto.Cryptor.decrypt;
import static utils.crypto.Cryptor.encrypt;

import java.time.LocalDate;

/**
 * Po for enterprise member.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class EnterpriseMemberPo extends MemberPo {

	protected EnterpriseMemberPo() {
		super(Type.BUSINESS);
	}

	@Override
	public MemberPo setBirthday(LocalDate birthday) {
		return this;
	}

	@Override
	public MemberPo setEnterprise(String enterprise) {
		this.enterprise = enterprise;
		return this;
	}

	@Override
	public LocalDate getBirthday() {
		return null;
	}

	@Override
	public String getEnterprise() {
		if (isValid())
			return this.enterprise;
		return null;
	}

	/**
	 * Get encrypted enterprise string. Automatically encrypt {@code enterprise}
	 * field. Just for hibernate. Make client requirement happy.<br>
	 * 
	 * @return encrypted enterprise string.
	 */
	public String getEncryptEnterprise() {
		return encrypt(enterprise);
	}

	/**
	 * Set encrypted enterprise string. Automatically decrypt
	 * {@code encryptEnterprise} field. Just for hibernate. Make client
	 * requirement happy.<br>
	 * 
	 * @param encryptEnterprise
	 *            encrpyted enterprise string
	 */
	public void setEncryptEnterprise(String encryptEnterprise) {
		this.enterprise = decrypt(encryptEnterprise);
	}
}
