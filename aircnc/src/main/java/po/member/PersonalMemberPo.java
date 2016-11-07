package po.member;

import java.time.LocalDate;

/**
 * Po of personal member<br>
 * 
 * @author ClevelandAlto
 *
 */
public class PersonalMemberPo extends MemberPo {
	protected PersonalMemberPo() {
		super(Type.PERSONAL);
	}

	@Override
	MemberPo setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	@Override
	MemberPo setEnterprise(String enterprise) {
		return this;
	}

	@Override
	public LocalDate getBirthday() {
		if (isValid())
			return this.birthday;
		return null;
	}

	@Override
	public String getEnterprise() {
		return null;
	}

}
