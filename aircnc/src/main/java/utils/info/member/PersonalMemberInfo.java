package utils.info.member;

import java.time.LocalDate;

public abstract class PersonalMemberInfo extends MemberInfo {

	public PersonalMemberInfo() {
		super(Type.PERSONAL);
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