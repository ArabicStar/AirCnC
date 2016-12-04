package presentation.member.accessor;

import java.time.LocalDate;

import vo.member.MemberVo;

public interface RegisterPersonAccessor {
	
	public MemberVo getPersonMemberVo();
	
	public void setUsername(String name);
	
	public void setPassword(String password);
	
	public void setBirthday(LocalDate date);
	
}
