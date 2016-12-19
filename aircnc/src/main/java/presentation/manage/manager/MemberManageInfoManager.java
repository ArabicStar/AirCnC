package presentation.manage.manager;

import javafx.collections.ObservableList;
import presentation.manage.model.MemberManageModel;
import vo.member.MemberVo;

public interface MemberManageInfoManager {
	
	public boolean setUser(MemberVo vo);
	
	public ObservableList<MemberManageModel> getMemberInfo();
}
