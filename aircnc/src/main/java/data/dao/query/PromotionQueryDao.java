package data.dao.query;

import java.util.Set;

import po.promotion.PromotionPo;

public interface PromotionQueryDao {
	public Set<PromotionPo> getHotelAllPromotions(int hotelId);

	public Set<PromotionPo> getWebsiteAllPromotions();
}
