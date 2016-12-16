package data.dao.promotion;

import po.promotion.WebsitePromotionPo;

public interface WebsitePromotionDao {
	public boolean addWebsitePromotion(WebsitePromotionPo po);

	public boolean deleteWebsitePromotion(long id);

	public boolean updateWebsitePromotion(WebsitePromotionPo po);

	public WebsitePromotionPo findWebsitePromotion(long id);
}
