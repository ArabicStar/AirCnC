package presentation.hotel.accessor;

import vo.promotion.PromotionVo;

public interface HotelPromotionAccessor {
	public void setPractical(long promotionId,boolean practical);
	
	
	public void setPromotion(PromotionVo vo);
	
	public void setDeletePromotion(long promotionId);
	
	public long getPromotionId();
	
	public boolean getPractical();
	
	public PromotionVo getPromotion();

}
