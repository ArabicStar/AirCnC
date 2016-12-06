package data.dao.member;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.packedRmiEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.query.CreditQueryDao;
import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class MemberDaoProxy extends AccessSecureProxy implements CreditDao, MemberDao {
	/* singleton */
	/**
	 * Singleton instance
	 */
	private static MemberDaoProxy instance;

	public static final MemberDaoProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberDaoProxy(clientId);
	}

	public static final MemberDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */

	/**
	 * Actual credit dao handler
	 */
	private RemoteCreditDao creditDao;
	/**
	 * Actual member dao handler
	 */
	private RemoteMemberDao memberDao;

	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	private MemberDaoProxy(Client clientId) {
		super(clientId);
	}

	/**
	 * Load a spefic member dao, auth to cilents of user and market.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implemention
	 */
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public void loadRemoteMemberDao(RemoteMemberDao memberDao) {
		checkAuthentication();

		this.memberDao = memberDao;
	}

	/**
	 * Load a spefic credit dao, auth to clients of user, hotel and market, plus
	 * server.<br>
	 */
	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public void loadRemoteCreditDao(RemoteCreditDao creditDao) {
		checkAuthentication();

		this.creditDao = creditDao;
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean addMember(MemberPo po) {
		checkAuthentication();

		try {
			return memberDao.addMember(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	/* As follow are proxy methods. */
	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteMember(String id) {
		checkAuthentication();

		try {
			return memberDao.deleteMember(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateMember(MemberPo po) {
		checkAuthentication();

		try {
			return memberDao.updateMember(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public MemberPo findMember(String id) {
		checkAuthentication();

		try {
			return memberDao.findMember(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public boolean existsMember(String id) {
		checkAuthentication();

		try {
			return memberDao.existsMember(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.SERVER, Client.MARKET })
	public MemberPo changeCredit(CreditChangePo aChange) {
		checkAuthentication();

		try {
			return creditDao.changeCredit(aChange);
		} catch (RemoteException e) {
			throw packedRmiEx(e);
		}
	}
}
