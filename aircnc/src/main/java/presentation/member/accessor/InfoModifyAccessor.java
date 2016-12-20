package presentation.member.accessor;

import utils.info.member.MemberInfo;
import vo.member.MemberVo;

public interface InfoModifyAccessor {
	
	public MemberVo getModifiedMemberVo();
	
	public int getPasswordHash();
	
	public void setUser(MemberInfo info);
	
	public void setName(String name);
	
	public void setPassword(String password);
	
	public void setEmail(String email);
	
	public void setMobi(String mobi);
	
	public void setTele(String tele);
	
}
