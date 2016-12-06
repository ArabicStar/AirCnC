package rmi;

import static utils.exception.StaticExceptionFactory.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import utils.logger.Log;

public final class RemoteHelper {
	private static RemoteHelper instance;

	public static final void launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		Log.d("remote helper start to work at port:" + PORT);
		instance = new RemoteHelper();
		instance.init();
	}

	public static final RemoteHelper getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private RemoteHelper() {
	}

	private void init() {
		fetchMemberDaoRemoteObj();
		fetchQueryDaoRemoteObj();
	}

	private void fetchQueryDaoRemoteObj() {
		queryDaoRemoteObj = findRemote("RemoteQueryDao");

		Log.d("fetch query dao remote obj " + (queryDaoRemoteObj == null ? "succeed" : "failed"));
	}

	private void fetchMemberDaoRemoteObj() {
		memberDaoRemoteObj = findRemote("RemoteMemberDao");

		Log.d("fetch member dao remote obj " + (memberDaoRemoteObj == null ? "succeed" : "failed"));
	}

	private Remote memberDaoRemoteObj;
	private Remote queryDaoRemoteObj;

	public RemoteMemberDao getRemoteMemberDao() {
		return (RemoteMemberDao) memberDaoRemoteObj;
	}

	public RemoteCreditDao getRemoteCreditDao() {
		return (RemoteCreditDao) memberDaoRemoteObj;
	}

	public RemoteCreditQueryDao getRemoteCreditQueryDao() {
		return (RemoteCreditQueryDao) queryDaoRemoteObj;
	}

	private static final int PORT = 8888;
	private static final StringBuilder sb = new StringBuilder("rmi://localhost:").append(PORT).append("/");

	private static final Remote findRemote(String name) {
		final String url = sb.append(name).toString();
		try {
			return Naming.lookup(url);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Log.e("RMI Exception", e);
		}
		return null;
	}
}
