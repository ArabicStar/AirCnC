package rmi.remote;

import static utils.exception.StaticExceptionFactory.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import rmi.RemoteHelper;

/**
 * Remote object of dao of member module.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class MemberDaoRemoteObj extends UnicastRemoteObject implements RemoteMemberDao, RemoteCreditDao {
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 5588095374487327431L;

	/* Singleton */
	/**
	 * Singleton instance
	 */
	private static MemberDaoRemoteObj obj;

	public static final void launch() throws RemoteException {
		if (obj != null)
			throw duplicateSingletonEx();

		final MemberDao memberDao = MemberDaoImpl.INSTANCE;
		final CreditDao creditDao = CreditDaoImpl.INSTANCE;

		obj = new MemberDaoRemoteObj(memberDao, creditDao);

		RemoteHelper.bindRemoteObj("RemoteMemberDao", obj);
	}
	/* Singleton */

	private MemberDao memberDao;
	private CreditDao creditDao;

	private MemberDaoRemoteObj(MemberDao memberDao, CreditDao creditDao) throws RemoteException {
		super();
		this.memberDao = memberDao;
		this.creditDao = creditDao;
	}

	public boolean addMember(MemberPo po) throws RemoteException {
		return memberDao.addMember(po);
	}

	public boolean deleteMember(String id) throws RemoteException {
		return memberDao.deleteMember(id);
	}

	public boolean updateMember(MemberPo po) throws RemoteException {
		return memberDao.updateMember(po);
	}

	public MemberPo findMember(String id) throws RemoteException {
		return memberDao.findMember(id);
	}

	public boolean existsMember(String id) throws RemoteException {
		return memberDao.existsMember(id);
	}

	public MemberPo changeCredit(CreditChangePo changePo) {
		return creditDao.changeCredit(changePo);
	}

}
