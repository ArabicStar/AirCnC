package presentation.manage.manager;

import java.util.List;

import vo.promotion.HotelPromotionVo;

public interface HotelManagePromotionManager {

	public boolean setPromotion(List<HotelPromotionVo> promotions);
	
	public List<String> getDescription();
}
