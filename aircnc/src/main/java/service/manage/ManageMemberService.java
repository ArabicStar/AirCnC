package service.manage;

import utils.info.member.MemberInfo;

public interface ManageMemberService {
	
	public boolean ModifyMemberInfo(MemberInfo memberInfo);
	
	public MemberInfo getMemberInfo(String id);
}
