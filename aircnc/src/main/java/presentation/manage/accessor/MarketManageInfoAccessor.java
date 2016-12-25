package presentation.manage.accessor;

import vo.market.MarketVo;

public interface MarketManageInfoAccessor {
	
	public MarketVo getModifiedMarketVo();
	
	public MarketVo getAddedMarketVo();
	
	public int getPasswordHash();
	
	public void setPassword(String password);
	
	public String getMarketId();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setMarketVo(MarketVo vo);

}
