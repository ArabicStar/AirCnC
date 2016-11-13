package service;

import vo.market.MarketVo;

public interface MarketService {

	/**
	 * @param marketId
	 * @return	获取营销数据
	 */
	public MarketVo getMarket(String marketId);


}
