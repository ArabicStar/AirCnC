package presentation.hotel.accessor;

import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;

public interface HotelPromotionAccessor {
	public void setPractical(long promotionId,boolean practical);
	
	
	public void setPromotion(HotelPromotionVo vo);
	
	public void setDeletePromotion(long promotionId);
	
	public long getPromotionId();
	
	public boolean getPractical();
	
	public HotelPromotionVo getPromotion();

}
