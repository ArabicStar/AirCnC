package utils.info.member;

import java.time.LocalDate;

public abstract class EnterpriseMemberInfo extends MemberInfo {

	protected EnterpriseMemberInfo() {
		super(Type.BUSINESS);
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
