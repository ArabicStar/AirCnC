package service.promotion;

import java.util.Set;

import vo.promotion.PromotionVo;

public interface WebsitePromotionInfoService {
	public Set<PromotionVo> getUserAvailableWebsitePromotions();

	public PromotionVo getWebsitePromotion(long id);

	public void refreshBuffer();
}
