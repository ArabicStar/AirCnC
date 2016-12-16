package presentation.manage.accessor;

import java.time.LocalDate;

import presentation.manage.model.MemberManageModel;
import vo.member.MemberVoBuilder;

public interface MemberManageInfoAccessor {
	
	public MemberVoBuilder getModifiedMemberVo();
	
	public String getMemberId();
	
	public void setId(String id);
	
	public void setBirthday(LocalDate birthday);
	
	public void setEnterprise(String enterprise);
	
	public void setMemberModel(MemberManageModel model);
	
}
