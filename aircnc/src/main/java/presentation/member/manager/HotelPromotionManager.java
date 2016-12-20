package presentation.member.manager;

import java.util.List;
import java.util.Set;

import vo.promotion.PromotionVo;

public interface HotelPromotionManager {
	
	public boolean setPromotion(Set<PromotionVo> promotions);
	
	public List<String> getDescription();
}
