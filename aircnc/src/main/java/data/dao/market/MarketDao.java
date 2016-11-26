package data.dao.market;

import po.market.MarketPo;

public interface MarketDao {
	public boolean addMarket(MarketPo po);

	public boolean deleteMarket(String id);

	public boolean updateMarket(MarketPo po);

	public MarketPo findMarket(String id);

	public boolean existsMarket(String id);
}
