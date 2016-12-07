package presentation.manage.manager;

import presentation.manage.model.MemberManageModel;
import vo.member.MemberVo;

public interface MemberManageInfoManager {
	
	public boolean setUser(MemberVo vo);
	
	public MemberManageModel getMemberInfo();
}
