package presentation.manage.accessor;

import java.time.LocalDate;

import vo.member.MemberVo;

public interface MemberManageInfoAccessor {
	
	public MemberVo getModifiedMemberVo();
	
	public String getMemberId();
	
	public void setId(String id);
	
	public void setBirthday(LocalDate birthday);
	
	public void setEnterprise(String enterprise);
	
	public void setMemberVo(MemberVo vo);
	
}
