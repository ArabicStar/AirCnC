package data.dao.member;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;

/*final*/
public class MemberDaoProxy implements CreditDao, MemberDao {
	/* singleton */
	/**
	 * Singleton instance
	 */
	private static MemberDaoProxy instance;

	public static final MemberDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberDaoProxy();
	}

	public static final MemberDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */

	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	private MemberDaoProxy() {
	}

	/*
	 ****************************
	 ******* MemberDao*******
	 ****************************
	 */
	/**
	 * /*
	 ***************************
	 ******* MemberDao******
	 ***************************
	 */
	/**
	 * Actual member dao handler
	 */
	private RemoteMemberDao memberDao;

	/**
	 * Load a spefic member dao, auth to cilents of user and market.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implemention
	 */

	public void loadRemoteMemberDao(RemoteMemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public boolean addMember(MemberPo po) {
		return hazard(() -> memberDao.addMember(po));
	}

	/* As follow are proxy methods. */
	@Override

	public boolean deleteMember(String id) {
		return hazard(() -> memberDao.deleteMember(id));
	}

	@Override
	public boolean updateMember(MemberPo po) {
		return hazard(() -> memberDao.updateMember(po));
	}

	@Override
	public MemberPo findMember(String id) {
		return hazard(() -> memberDao.findMember(id));
	}

	@Override
	public boolean existsMember(String id) {
		return hazard(() -> memberDao.existsMember(id));
	}

	/*
	 *************************
	 ******* CreditDao*******
	 *************************
	 */
	/**
	 * Actual credit dao handler
	 */
	private RemoteCreditDao creditDao;

	/**
	 * Load a spefic credit dao, auth to clients of user, hotel and market, plus
	 * server.<br>
	 */

	public void loadRemoteCreditDao(RemoteCreditDao creditDao) {
		this.creditDao = creditDao;
	}

	@Override
	public MemberPo changeCredit(CreditChangePo aChange) {
		return hazard(() -> {
			return creditDao.changeCredit(aChange);
		});
	}

	@Override
	public int deleteAllCredit() {
		return hazard(() -> {
			return creditDao.deleteAllCredit();
		});
	}
}
