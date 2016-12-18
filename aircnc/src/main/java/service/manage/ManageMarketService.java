package service.manage;

import utils.info.market.MarketInfo;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public interface ManageMarketService {
	
	public MarketVo AddMarketInfo(MarketVoBuilder newMarketInfo, int passwordHash);
	
	public boolean ModifyMarketInfo(MarketInfo marketInfo);
	
	public boolean deleteMarketInfo(MarketInfo marketInfo);
	
	public boolean deleteMarketInfo(String id);
}
