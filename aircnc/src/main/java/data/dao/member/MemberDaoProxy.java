package data.dao.member;

import java.util.List;

import data.dao.query.CreditQueryDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class MemberDaoProxy extends AccessSecureProxy implements CreditDao, MemberDao, CreditQueryDao {
	/**
	 * Actual credit dao handler
	 */
	private CreditDao creditDao;
	/**
	 * Actuak member dao handler
	 */
	private MemberDao memberDao;
	private CreditQueryDao creditQueryDao;

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
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public abstract void loadMemberDao();

	/**
	 * Load a spefic member dao, for convinience of pontential change.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implemention
	 */
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
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

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public abstract void loadCreditQueryDao();

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public void loadCreditQueryDao(CreditQueryDao creditQueryDao) {
		checkAuthentication();

		this.creditQueryDao = creditQueryDao;
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

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<CreditChangePo> searchByMemberId(String memberId) {
		checkAuthentication();

		return creditQueryDao.searchByMemberId(memberId);
	}
}
