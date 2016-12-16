package service.promotion;

import java.util.Set;

import vo.promotion.PromotionVo;

public interface HotelPromotionInfoService {
	public Set<PromotionVo> getUserAvailablePromotions(int hotelId);
}
