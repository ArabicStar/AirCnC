package service.market;

import utils.info.market.MarketInfo;

public interface MarketInfoService {
	public MarketInfo getMarketInfo(String id);

	public boolean updateInfo(MarketInfo modifiedInfo);
}
