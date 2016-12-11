package presentation.member.manager;

import presentation.member.model.MemberInfoModel;
import utils.info.member.MemberInfo;

public interface UserInfoManager {
	
	public boolean setUser(MemberInfo vo);
	
	public MemberInfoModel getMemberInfo();
}
