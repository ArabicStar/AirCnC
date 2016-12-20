package po.member;

import java.time.LocalDate;

/**
 * Po of personal member<br>
 * 
 * @author ClevelandAlto
 *
 */
public class PersonalMemberPo extends MemberPo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1606964798670677995L;

	protected PersonalMemberPo() {
		super(Type.PERSONAL);
	}

	@Override
	public MemberPo setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	@Override
	public MemberPo setEnterprise(String enterprise) {
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
