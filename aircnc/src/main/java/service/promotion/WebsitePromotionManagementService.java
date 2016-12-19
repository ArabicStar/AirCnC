package service.promotion;

import java.util.Set;

import vo.promotion.PromotionVo;
import vo.promotion.WebsitePromotionVo;

public interface WebsitePromotionManagementService {
	public boolean addWebsitePromotion(WebsitePromotionVo vo);

	public boolean deleteWebsitePromotion(WebsitePromotionVo vo);

	public boolean updateWebsitePromotion(WebsitePromotionVo vo);

	public Set<PromotionVo> getWebsiteAllPromotions();

	public Set<PromotionVo> getWebsiteActivePromotion();
}
