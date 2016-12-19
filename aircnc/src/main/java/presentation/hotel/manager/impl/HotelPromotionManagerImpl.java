package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import presentation.hotel.manager.HotelPromotionManager;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.hotel.HotelWhen;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVoBuilder;

public class HotelPromotionManagerImpl implements HotelPromotionManager{
	private static HotelPromotionManagerImpl instance;
	
	private List<HotelPromotionVo> promotions;
	
	private List<String> descriptions;
	
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
	public boolean setPromotion(List<HotelPromotionVo> promotions) {
		if(promotions!=null){
			this.promotions=promotions;
			return true;
		}
		return false;
		
	}

	@Override
	public List<String> getDescription() {
		test();
		
		return promotions.stream().map(HotelPromotionVo::description).
				collect(Collectors.toList());
	}
	
	private void test(){
		
		PromotionVoBuilder builder = new PromotionVoBuilder(Scope.Hotel).setId(123).
				setHotelId(2).setName("双十一特惠").setPractical(true);
		builder.when(HotelWhen.BIRTHDAY).setParam(TriggerParams.BIRTHDAY,LocalDate.now());
		builder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.8);
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
		
		builder.when(HotelWhen.ENTERPRISE).setParam(TriggerParams.ENTERPRISE, "南京大学");
		builder.how(How.CONST).setParam(ApplierParams.AMOUNT, 100);	
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
		
		builder.when(HotelWhen.MULTI_ROOMS).setParam(TriggerParams.FROM,LocalDate.now()).
		setParam(TriggerParams.TO, LocalDate.now().plusDays(5));
		builder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.9);
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
		
		promotions.add((HotelPromotionVo) builder.getPromotionInfo());
	}

}
