package presentation.market.manage;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import presentation.market.model.WebsitePromotionModel;
import vo.promotion.PromotionVo;

public interface MarketPromotionManager {
	public boolean setPromotion(Set<PromotionVo> promotions);
	
	public List<String> getDescription();
	
	public ObservableList<WebsitePromotionModel> getPromotionList();
}
