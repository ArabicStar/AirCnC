package data.dao.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.packedRmiEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.rmi.query.RemoteCreditQueryDao;
import po.member.credit.CreditChangePo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class QueryDaoProxy extends AccessSecureProxy implements CreditQueryDao {
	private static QueryDaoProxy instance;

	public static final QueryDaoProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new QueryDaoProxy(clientId);
	}

	public static final QueryDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private QueryDaoProxy(Client clientId) {
		super(clientId);
	}

	private RemoteCreditQueryDao remoteCreditQueryDao;

	@AuthenticatePolicy(Client.USER)
	public void loadRemoteCreditQueryDao(RemoteCreditQueryDao remoteCreditQueryDao) {
		checkAuthentication();

		this.remoteCreditQueryDao = remoteCreditQueryDao;
	}

	@Override
	@AuthenticatePolicy(Client.USER)
	public List<CreditChangePo> searchByMemberId(String memberId) {
		checkAuthentication();

		try {
			return remoteCreditQueryDao.searchByMemberId(memberId);
		} catch (RemoteException re) {
			// e.printStackTrace();
			throw packedRmiEx(re);
		}
	}
}
