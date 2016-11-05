package service;

import vo.MarketVo;

public interface MarketService {

	/**
	 * @param marketId
	 * @return	获取营销数据
	 */
	public MarketVo getMarket(String marketId);


}
