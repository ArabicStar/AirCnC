package presentation.manage.manager;

import javafx.collections.ObservableList;
import presentation.manage.model.MarketManageModel;
import vo.market.MarketVo;

public interface MarketManageInfoManager {
	
	public boolean setMarket(MarketVo vo);
	
	public ObservableList<MarketManageModel> getMarketInfoList();
	
	public MarketManageModel getMarketInfo();
}
