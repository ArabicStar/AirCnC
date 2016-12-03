package vo.member;

import java.time.LocalDate;

/**
 * Vo for EnterpriseMember<br>
 * 
 * @author ClevelandAlto
 *
 */
public class EnterpriseMemberVo extends MemberVo {

	/**
	 * Default constructor, defines the type.
	 */
	EnterpriseMemberVo() {
		super(Type.BUSINESS);
	}

	@Override
	EnterpriseMemberVo setEnterprise(String enterprise) {
		this.enterprise = enterprise;
		return this;
	}

	@Override
	MemberVo setBirthday(LocalDate birthday) {
		return this;
	}

	@Override
	public LocalDate getBirthday() {
		return null;
	}

	@Override
	public String getEnterprise() {
		if (isValid())
			return enterprise;
		return null;
	}
}
