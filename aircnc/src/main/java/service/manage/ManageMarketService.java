package service.manage;

import utils.info.market.MarketInfo;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public interface ManageMarketService {
	
	public MarketVo AddMarketInfo(MarketVoBuilder newMarketInfo, int passwordHash);
	
	public boolean ModifyMarketInfo(MarketInfo marketInfo);
	
	public MarketInfo getMarketInfo(String id);
	
	public boolean deleteMarketInfo(String id);
}
