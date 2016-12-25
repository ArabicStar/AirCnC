package data.dao.market;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.rmi.market.RemoteMarketDao;
import po.market.MarketPo;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class MarketDaoProxy implements MarketDao{
	/* singleton */
	/**
	 * Singleton instance
	 */
	private static MarketDaoProxy instance;

	public static final MarketDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketDaoProxy();
	}

	public static final MarketDaoProxy getInstance() {
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
	private MarketDaoProxy() {
	}

	/*
	 ****************************
	 ******* MarketDao*******
	 ****************************
	 */
	/**
	 * /*
	 ***************************
	 ******* MarketDao******
	 ***************************
	 */
	/**
	 * Actual market dao handler
	 */
	private RemoteMarketDao marketDao;

	/**
	 * Load a specific market dao, auth to clients of user and market.<br>
	 * 
	 * @param marketDao
	 *            a specific market dao implementation
	 */
	public void loadRemoteMarketDao(RemoteMarketDao marketDao) {
		this.marketDao = marketDao;
	}

	@Override
	public boolean addMarket(MarketPo po) {
		return hazard(() -> {
			return marketDao.addMarket(po);
		});
	}

	/* As follow are proxy methods. */
	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteMarket(String id) {
		return hazard(() -> {
			return marketDao.deleteMarket(id);
		});
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateMarket(MarketPo po) {
		return hazard(() -> {
			return marketDao.updateMarket(po);
		});
	}
	
	@Override
	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public MarketPo findMarket(String id) {
		return hazard(() -> {
			return marketDao.findMarket(id);
		});
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public boolean existsMarket(String id) {
		return hazard(() -> {
			return marketDao.existsMarket(id);
		});
	}
}
