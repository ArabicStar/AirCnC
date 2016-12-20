package service.promotion;

import java.util.Set;

import vo.promotion.PromotionVo;

public interface HotelPromotionInfoService {
	public Set<PromotionVo> getUserAvailableHotelPromotions(int hotelId);

	public PromotionVo getHotelPromotion(long id);
}
