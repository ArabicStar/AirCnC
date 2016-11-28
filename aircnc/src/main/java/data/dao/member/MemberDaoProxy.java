package data.dao.member;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class MemberDaoProxy extends AccessSecureProxy implements CreditDao, MemberDao {
	private CreditDao creditDao;
	private MemberDao memberDao;

	protected MemberDaoProxy(Client clientId) {
		super(clientId);
	}

	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public abstract void loadMemberDao();

	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public void loadMemberDao(MemberDao memberDao) {
		checkAuthentication();

		this.memberDao = memberDao;

	}

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public abstract void loadCreditDao();

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public void loadCreditDao(CreditDao creditDao) {
		checkAuthentication();

		this.creditDao = creditDao;
	}

	@Override

	@AuthenticatePolicy({ Client.USER })
	public boolean addMember(MemberPo po) {
		checkAuthentication();

		return memberDao.addMember(po);
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteMember(String id) {
		checkAuthentication();

		return memberDao.deleteMember(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateMember(MemberPo po) {
		checkAuthentication();

		return memberDao.updateMember(po);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public MemberPo findMember(String id) {
		checkAuthentication();

		return memberDao.findMember(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public boolean existsMember(String id) {
		checkAuthentication();

		return memberDao.existsMember(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public MemberPo changeCredit(CreditChangePo aChange) {
		checkAuthentication();

		return creditDao.changeCredit(aChange);
	}
}
