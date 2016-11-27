package data.dao.member;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;

public abstract class MemberDaoProxy implements CreditDao, MemberDao {
	private CreditDao creditDao;
	private MemberDao memberDao;
	private MemberDaoAccess access;

	public MemberDaoProxy(MemberDaoAccess access) {
		this.access = access;
	}

	public abstract void loadMemberDao();

	public void loadMemberDao(MemberDao memberDao) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return;
		}

		this.memberDao = memberDao;

	}

	public abstract void loadCreditDao();

	public void loadCreditDao(CreditDao creditDao) {
		if (!access.isCreditDaoAccessed()) {
			System.err.println("CREDIT DAO ACCESS DENIED");
			return;
		}

		this.creditDao = creditDao;
	}

	@Override
	public boolean addMember(MemberPo po) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return false;
		}

		return memberDao.addMember(po);
	}

	@Override
	public boolean deleteMember(String id) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return false;
		}

		return memberDao.deleteMember(id);
	}

	@Override
	public boolean updateMember(MemberPo po) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return false;
		}

		return memberDao.updateMember(po);
	}

	@Override
	public MemberPo findMember(String id) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return null;
		}

		return memberDao.findMember(id);
	}

	@Override
	public boolean existsMember(String id) {
		if (!access.isMemberDaoAccessed()) {
			System.err.println("MEMBER DAO ACCESS DENIED");
			return false;
		}

		return memberDao.existsMember(id);
	}

	@Override
	public MemberPo changeCredit(CreditChangePo aChange) {
		if (!access.isCreditDaoAccessed()) {
			System.err.println("CREDIT DAO ACCESS DENIED");
			return null;
		}

		return creditDao.changeCredit(aChange);
	}
}
