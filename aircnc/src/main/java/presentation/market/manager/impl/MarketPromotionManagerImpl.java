package presentation.market.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.market.manager.MarketPromotionManager;
import presentation.market.model.WebsitePromotionModel;
import vo.promotion.PromotionVo;

public class MarketPromotionManagerImpl implements MarketPromotionManager{
	private static MarketPromotionManagerImpl instance;
	
	private Set<PromotionVo> promotions;
	
	private ObservableList<WebsitePromotionModel> promotionData;

	
	public static final MarketPromotionManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketPromotionManagerImpl();
	}
	
	public static final MarketPromotionManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public boolean setPromotion(Set<PromotionVo> promotions) {
		if(promotions!=null){
			this.promotions=promotions;
			return true;
		}
		return false;
	}

	@Override
	public List<String> getDescription() {
		
		return promotions.stream().filter(p->p.getPractical()).map(PromotionVo::description).
				collect(Collectors.toList());
	}

	@Override
	public ObservableList<WebsitePromotionModel> getPromotionList() {
		
		promotionData = FXCollections.observableArrayList();
//		test();
		Iterator<PromotionVo> it = promotions.iterator();
		while(it.hasNext())
			promotionData.add(new WebsitePromotionModel(it.next()));
		return promotionData;
	}

}
