package data.dao.query;

import java.util.Set;

import po.promotion.PromotionPo;

public interface PromotionQueryDao {
	public Set<PromotionPo> getHotelAllPromotions(int hotelId);

	public Set<PromotionPo> getWebsiteAllPromotions();

	public PromotionPo getHotelPromotion(long id);

	public PromotionPo getWebsitePromotion(long id);
}
