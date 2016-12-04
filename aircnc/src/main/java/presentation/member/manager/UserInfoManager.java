package presentation.member.manager;

import presentation.member.model.MemberInfoModel;
import vo.member.MemberVo;

public interface UserInfoManager {
	
	public boolean setUser(MemberVo vo);
	
	public MemberInfoModel getMemberInfo();
}
