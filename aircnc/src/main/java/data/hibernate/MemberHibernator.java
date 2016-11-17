package data.hibernate;

import po.member.MemberPo;

public interface MemberHibernator {
	public MemberPo findMember(int id);

	public boolean deleteMember(int id);

	public boolean updateMember(MemberPo po);

	public boolean addMember(MemberPo po);
}
