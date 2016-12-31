package data.dao.rmi.market;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.market.MarketPo;
import utils.info.level.LevelStrategy;

/**
 * Remote interface for market dao.<br>
 * 
 * @see data.dao.market.MarketDao
 * @author paranoia
 *
 */
public interface RemoteMarketDao extends Remote {

	public boolean addMarket(final MarketPo po) throws RemoteException;

	public boolean deleteMarket(final String id) throws RemoteException;

	public boolean updateMarket(final MarketPo po) throws RemoteException;

	public MarketPo findMarket(final String id) throws RemoteException;

	public boolean existsMarket(final String id) throws RemoteException;

	public LevelStrategy getLevelStrategy() throws RemoteException;

	public boolean updateLevelStrategy(LevelStrategy ls) throws RemoteException;
}
