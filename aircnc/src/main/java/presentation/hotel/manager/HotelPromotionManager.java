package presentation.hotel.manager;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import presentation.hotel.model.CheckOrderModel;
import presentation.hotel.model.HotelPromotionModel;
import vo.promotion.PromotionVo;

public interface HotelPromotionManager {

	public boolean setPromotion(Set<PromotionVo> promotions);
	
	public List<String> getDescription();
	
	public ObservableList<HotelPromotionModel> getPromotionList();
}
