package data.dao;

import po.MarketPo;

public interface MarketDao {
	/**
	 * @param marketId
	 * @return	获取营销用户信息
	 */
	public MarketPo getMarket(String marketId);

	/**
	 * @param marketPo
	 * @return	更新信息
	 */
	public boolean updateMarket(MarketPo marketPo);
}
