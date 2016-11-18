package data.dao;

import po.member.MemberPo;

public interface MemberDao {
	public boolean addMember(final MemberPo po);

	public boolean deleteMember(final String id);

	public boolean updateMember(final MemberPo po);

	public MemberPo findMember(final String id);

	public boolean existsMember(final String id);
}
