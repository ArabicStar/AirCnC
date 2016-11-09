package po.member;

import java.time.LocalDate;

/**
 * Po for enterprise member<br>
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

}
