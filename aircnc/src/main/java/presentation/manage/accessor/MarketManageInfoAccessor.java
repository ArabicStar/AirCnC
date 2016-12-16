package presentation.manage.accessor;

import presentation.manage.model.MarketManageModel;
import vo.market.MarketVoBuilder;

public interface MarketManageInfoAccessor {
	
	public MarketVoBuilder getModifiedMarketVo();
	
	public String getMarketId();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setPassword(String password);
	
	public void setMarketModel(MarketManageModel model);
}
