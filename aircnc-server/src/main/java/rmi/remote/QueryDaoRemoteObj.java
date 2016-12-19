package rmi.remote;

import static utils.exception.StaticExceptionFactory.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import data.dao.impl.member.CreditDaoImpl;
import data.dao.query.CreditQueryDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import po.member.credit.CreditChangePo;
import rmi.RemoteHelper;

public class QueryDaoRemoteObj extends UnicastRemoteObject implements RemoteCreditQueryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 828312069380719877L;
	/* Singleton */
	/**
	 * Singleton instance
	 */
	private static QueryDaoRemoteObj instance;

	public static final void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final CreditQueryDao creditQuery = CreditDaoImpl.INSTANCE;

		instance = new QueryDaoRemoteObj(creditQuery);

		RemoteHelper.bindRemoteObj("RemoteQueryDao", instance);
	}

	private QueryDaoRemoteObj(CreditQueryDao creditQuery) throws RemoteException {
		super();
		this.creditQuery = creditQuery;
	}
	/* Singleton */

	/*
	 ********************************
	 ******* CreditQueryDao*******
	 ********************************
	 */
	private CreditQueryDao creditQuery;

	@Override
	public List<CreditChangePo> searchByMemberId(String memberId) throws RemoteException {
		return creditQuery.searchByMemberId(memberId);
	}

	@Override
	public int getMemberCredit(String memberId) throws RemoteException {
		return creditQuery.getMemberCredit(memberId);
	}

}
