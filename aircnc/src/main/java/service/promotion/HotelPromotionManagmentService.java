package service.promotion;

import java.util.Set;

import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;

public interface HotelPromotionManagmentService {
	public boolean addPromotion(HotelPromotionVo vo);

	public boolean deletePromotion(HotelPromotionVo vo);

	public Set<PromotionVo> getAllPromotions(int hotelId);

	public Set<PromotionVo> getActivePromotion(int hotelId);

	public boolean updatePromotion(HotelPromotionVo vo);
}
