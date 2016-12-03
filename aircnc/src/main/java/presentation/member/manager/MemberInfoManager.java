package presentation.member.manager;

import vo.member.MemberVo;

public interface MemberInfoManager {
	
	public boolean setUser(MemberVo vo);
	
	public MemberVo getMemberVo();
	
	public String getUsername();
	
	public String getTele();
	
	public String getMobi();
	
	public String getEmail();
	
	public int getCredit();
}
