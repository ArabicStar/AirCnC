package service.promotion;

import java.util.Set;

import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;

public interface HotelPromotionManagmentService {
	public boolean addHotelPromotion(HotelPromotionVo vo);

	public boolean deleteHotelPromotion(HotelPromotionVo vo);

	public boolean updateHotelPromotion(HotelPromotionVo vo);

	public Set<PromotionVo> getHotelAllPromotions(int hotelId);

	public Set<PromotionVo> getHotelActivePromotion(int hotelId);
}
