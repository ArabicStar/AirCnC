package presentation.member.accessor;

import vo.member.MemberVo;

public interface InfoModifyAccessor {
	
	public MemberVo getModifiedMemberVo();
	
	public void setName(String name);
	
	public void setPassword(String password);
	
	public void setEmail(String email);
	
	public void setMobi(String mobi);
	
	public void setTele(String tele);
	
	public void setMemberVo(MemberVo vo);
	
}
