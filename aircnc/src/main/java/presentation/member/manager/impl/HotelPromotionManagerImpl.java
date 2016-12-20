package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import presentation.member.manager.HotelPromotionManager;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.hotel.HotelWhen;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

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
		test();
		
		return promotions.stream().map(PromotionVo::description).
				collect(Collectors.toList());
	}
	
	private void test(){
		promotions = new HashSet<PromotionVo>();
		LocalDateTime now = LocalDateTime.now();
		PromotionVoBuilder builder = new PromotionVoBuilder(Scope.Hotel).setId(123).
				setHotelId(2).setName("企业优惠").setPractical(true);
		
		builder.when(HotelWhen.DURING_PERIOD).setParam(TriggerParams.FROM,now.plusDays(1)).setParam(TriggerParams.TO,now.plusDays(5));
		builder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.8);
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());

		builder.when(HotelWhen.MULTI_ROOMS).setParam(TriggerParams.ROOM_NUM_THRESHOLD, 5);
		builder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.9);
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
		
		builder.when(HotelWhen.ENTERPRISE).setParam(TriggerParams.ENTERPRISE,"南京大学" );
		builder.how(How.CONST).setParam(ApplierParams.AMOUNT, 10.0);
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
		
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
	}

}
