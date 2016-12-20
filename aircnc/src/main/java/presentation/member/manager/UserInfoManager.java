package presentation.member.manager;

import presentation.member.model.MemberInfoModel;
import utils.info.member.MemberInfo;
import vo.member.MemberVo;

public interface UserInfoManager {
	
	public boolean setUser(MemberInfo vo);
	
	public MemberInfoModel getMemberInfo();
	
	public MemberVo getMemberVo();
}
