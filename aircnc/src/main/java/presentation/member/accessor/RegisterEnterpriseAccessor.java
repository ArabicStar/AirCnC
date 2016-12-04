package presentation.member.accessor;

import vo.member.MemberVo;

public interface RegisterEnterpriseAccessor {
	
	public MemberVo getEnterpriseMemberVo();
	
	public void setUsername(String name);
	
	public void setPassword(String password);
	
	public void setEnterprise(String enterprise);
}
