package data.dao;

import po.promotion.PromotionPo;

public interface PromotionDao {
	public boolean addPromotion(PromotionPo po);

	public boolean deletePromotion(String id);

	public boolean updatePromotion(PromotionPo po);

	public PromotionPo findPromotion(String id);

}
