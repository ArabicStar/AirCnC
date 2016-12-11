package presentation.member.accessor;

import presentation.member.model.MemberInfoModel;
import vo.member.MemberVo;

public interface InfoModifyAccessor {
	
	public MemberVo getModifiedMemberVo();
	
	public void setName(String name);
	
	public void setPassword(String password);
	
	public void setEmail(String email);
	
	public void setMobi(String mobi);
	
	public void setTele(String tele);
	
	public void setMemberModel(MemberInfoModel model);
	
	public int getPasswordHash();
	
}
