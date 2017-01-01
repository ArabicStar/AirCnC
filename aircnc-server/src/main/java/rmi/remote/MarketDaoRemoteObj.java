package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.impl.market.MarketDaoImpl;
import data.dao.market.MarketDao;
import data.dao.rmi.market.RemoteMarketDao;
import po.market.MarketPo;
import rmi.RemoteHelper;
import utils.info.level.LevelStrategy;

public class MarketDaoRemoteObj extends UnicastRemoteObject implements RemoteMarketDao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MarketDaoRemoteObj instance;

	public static final void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final MarketDao memberDao = MarketDaoImpl.INSTANCE;

		instance = new MarketDaoRemoteObj(memberDao);

		RemoteHelper.bindRemoteObj("RemoteMarketDao", instance);
	}
	
	private MarketDao marketDao;

	private MarketDaoRemoteObj(MarketDao marketDao) throws RemoteException {
		super();
		this.marketDao = marketDao;
	}
	
	@Override
	public boolean addMarket(MarketPo po) throws RemoteException {
		return marketDao.addMarket(po);
	}

	@Override
	public boolean deleteMarket(String id) throws RemoteException {
		return marketDao.deleteMarket(id);
	}

	@Override
	public boolean updateMarket(MarketPo po) throws RemoteException {
		return marketDao.updateMarket(po);
	}

	@Override
	public MarketPo findMarket(String id) throws RemoteException {
		return marketDao.findMarket(id);
	}

	@Override
	public boolean existsMarket(String id) throws RemoteException {
		return marketDao.existsMarket(id);
	}

	@Override
	public LevelStrategy getLevelStrategy() throws RemoteException {
		return marketDao.getLevelStrategy();
	}

	@Override
	public boolean updateLevelStrategy(LevelStrategy ls) throws RemoteException {
		return marketDao.updateLevelStrategy(ls);
	}

}
