package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import presentation.member.manager.HotelPromotionManager;
import vo.promotion.PromotionVo;

public class HotelPromotionManagerImpl implements HotelPromotionManager{
	private static HotelPromotionManagerImpl instance;
	
	private Set<PromotionVo> promotions;
	
	public static final HotelPromotionManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionManagerImpl();
	}
	
	public static final HotelPromotionManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
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
		return promotions.stream().map(PromotionVo::description).
				collect(Collectors.toList());
	}

}
