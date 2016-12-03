package vo.member;

import java.time.LocalDate;

public class PersonalMemberVo extends MemberVo {

	/**
	 * Default constructor, defines the type.
	 */
	PersonalMemberVo() {
		super(Type.PERSONAL);
	}

	@Override
	MemberVo setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	@Override
	MemberVo setEnterprise(String enterprise) {
		return this;
	}

	@Override
	public LocalDate getBirthday() {
		if (isValid())
			return birthday;
		return null;
	}

	@Override
	public String getEnterprise() {
		return null;
	}

}
