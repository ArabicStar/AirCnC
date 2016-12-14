package presentation.member.accessor;

import java.time.LocalDate;

import vo.member.MemberVoBuilder;

public interface RegisterAccessor {
	public MemberVoBuilder getNewAccountInfo();

	public int getPasswordHash();
	
	public void setUsername(String name);

	public void setPassword(String password);
	
	public void setType(String type);
	
	public void setBirthday(LocalDate birthday);
	
	public void setEnterprise(String enterprise);
}
