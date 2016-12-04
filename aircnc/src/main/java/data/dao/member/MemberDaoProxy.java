package data.dao.member;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class MemberDaoProxy extends AccessSecureProxy implements CreditDao, MemberDao {
	/**
	 * Actual credit dao handler
	 */
	private CreditDao creditDao;
	/**
	 * Actuak member dao handler
	 */
	private MemberDao memberDao;

	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	protected MemberDaoProxy(Client clientId) {
		super(clientId);
	}

	/**
	 * Load actual member dao, auth to cilents of user and market
	 */
	@AuthenticatePolicy({ Client.USER, Client.MARKET ,Client.MANAGE})
	public abstract void loadMemberDao();

	/**
	 * Load a spefic member dao, for convinience of pontential change.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implemention
	 */
	@AuthenticatePolicy({ Client.USER, Client.MARKET,Client.MANAGE })
	public void loadMemberDao(MemberDao memberDao) {
		checkAuthentication();

		this.memberDao = memberDao;

	}

	/**
	 * Load actual credit dao, auth to clients of user, hotel and market, plus
	 * server.<br>
	 */
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

	/* As follow are proxy methods. */
	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteMember(String id) {
		checkAuthentication();

		return memberDao.deleteMember(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER ,Client.MANAGE})
	public boolean updateMember(MemberPo po) {
		checkAuthentication();

		return memberDao.updateMember(po);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET ,Client.MANAGE})
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
