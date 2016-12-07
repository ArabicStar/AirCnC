package presentation.manage.manager;

import presentation.manage.model.MarketManageModel;
import vo.market.MarketVo;

public interface MarketManageInfoManager {
	
	public boolean setMarket(MarketVo vo);
	
	public MarketManageModel getMarketInfo();
}
