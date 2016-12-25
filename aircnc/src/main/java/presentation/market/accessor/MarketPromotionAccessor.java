package presentation.market.accessor;

import vo.promotion.WebsitePromotionVo;

public interface MarketPromotionAccessor {
	public void setPractical(long promotionId,boolean practical);
	
	public void setPromotion(WebsitePromotionVo vo);
	
	public void setDeletePromotion(long promotionId);
	
	public long getPromotionId();
	
	public boolean getPractical();
	
	public WebsitePromotionVo getPromotion();
}
