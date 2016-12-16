package service.promotion;

import java.util.Set;

import vo.promotion.PromotionVo;

public interface WebsitePromotionInfoService {
	public Set<PromotionVo> getUserAvailablePromotions();

	public void refreshBuffer();
}
