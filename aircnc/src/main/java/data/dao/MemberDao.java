package data.dao;

import po.member.MemberPo;

public interface MemberDao {
	public boolean addMember(MemberPo po);

	public boolean deleteMember(String id);

	public boolean updateMember(MemberPo po);

	public MemberPo findMember(String id);

	public boolean existsMember(String id);
}
