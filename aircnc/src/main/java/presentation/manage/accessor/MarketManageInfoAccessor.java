package presentation.manage.accessor;

import vo.market.MarketVo;

public interface MarketManageInfoAccessor {
	
	public MarketVo getModifiedMarketVo();
	
	public String getMarketId();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setMemberVo(MarketVo vo);

}
