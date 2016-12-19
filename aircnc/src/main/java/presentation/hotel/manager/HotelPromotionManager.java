package presentation.hotel.manager;

import java.util.List;

import vo.promotion.HotelPromotionVo;

public interface HotelPromotionManager {

	public boolean setPromotion(List<HotelPromotionVo> promotions);
	
	public List<String> getDescription();
}
