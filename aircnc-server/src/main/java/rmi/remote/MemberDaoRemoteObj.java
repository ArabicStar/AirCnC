package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import plugins.LevelCalc;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import rmi.RemoteHelper;

/**
 * Remote object of dao of member module.<br>
 * 
 * @author ClevelandAlto
 *
 */
/* final */
public class MemberDaoRemoteObj extends UnicastRemoteObject implements RemoteMemberDao, RemoteCreditDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -835381135222502344L;
	/* Singleton */
	private static MemberDaoRemoteObj instance;

	public static final void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final MemberDao memberDao = MemberDaoImpl.INSTANCE;
		final CreditDao creditDao = CreditDaoImpl.INSTANCE;

		instance = new MemberDaoRemoteObj(memberDao, creditDao);

		RemoteHelper.bindRemoteObj("RemoteMemberDao", instance);
	}
	/* Singleton */

	/*
	 ****************************
	 ******* MemberDao*******
	 ****************************
	 */
	private MemberDao memberDao;

	private MemberDaoRemoteObj(MemberDao memberDao, CreditDao creditDao) throws RemoteException {
		super();
		this.memberDao = memberDao;
		this.creditDao = creditDao;
	}

	@Override
	public boolean addMember(MemberPo po) throws RemoteException {
		return memberDao.addMember(po);
	}

	@Override
	public boolean deleteMember(String id) throws RemoteException {
		return memberDao.deleteMember(id);
	}

	@Override
	public boolean updateMember(MemberPo po) throws RemoteException {
		return memberDao.updateMember(po);
	}

	@Override
	public MemberPo findMember(String id) throws RemoteException {
		return LevelCalc.calcLevel(memberDao.findMember(id));
	}

	@Override
	public boolean existsMember(String id) throws RemoteException {
		return memberDao.existsMember(id);
	}

	/*
	 *************************
	 ******* CreditDao*******
	 *************************
	 */
	private CreditDao creditDao;

	@Override
	public MemberPo changeCredit(CreditChangePo changePo) throws RemoteException {
		return creditDao.changeCredit(changePo);
	}

	@Override
	public int deleteAllCredit() throws RemoteException {
		return creditDao.deleteAllCredit();
	}

}
