package data.dao.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.packedRmiEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.rmi.RemoteException;

import data.dao.rmi.market.RemoteMarketDao;
import po.market.MarketPo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class MarketDaoProxy extends AccessSecureProxy implements MarketDao{
	/* singleton */
	/**
	 * Singleton instance
	 */
	private static MarketDaoProxy instance;

	public static final MarketDaoProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketDaoProxy(clientId);
	}

	public static final MarketDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */
	/**
	 * Actual market dao handler
	 */
	private RemoteMarketDao marketDao;

	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	private MarketDaoProxy(Client clientId) {
		super(clientId);
	}

	/**
	 * Load a specific market dao, auth to clients of user and market.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implementation
	 */
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public void loadRemoteMarketDao(RemoteMarketDao marketDao) {
		checkAuthentication();

		this.marketDao = marketDao;
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean addMarket(MarketPo po) {
		checkAuthentication();

		try {
			return marketDao.addMarket(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	/* As follow are proxy methods. */
	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteMarket(String id) {
		checkAuthentication();

		try {
			return marketDao.deleteMarket(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateMarket(MarketPo po) {
		checkAuthentication();

		try {
			return marketDao.updateMarket(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public MarketPo findMarket(String id) {
		checkAuthentication();

		try {
			return marketDao.findMarket(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public boolean existsMarket(String id) {
		checkAuthentication();

		try {
			return marketDao.existsMarket(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
	}
}
