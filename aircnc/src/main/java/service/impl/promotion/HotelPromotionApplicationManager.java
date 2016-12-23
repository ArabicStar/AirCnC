package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import service.promotion.HotelPromotionApplicationService;
import service.promotion.HotelPromotionInfoService;
import utils.info.order.OrderInfo;
import utils.promotion.OrderRelatedInfoHelper;
import utils.promotion.Promotion;
import vo.order.OrderVoBuilder;
import vo.promotion.PromotionVo;

public class HotelPromotionApplicationManager implements HotelPromotionApplicationService {
	/* Singleton */
	private static HotelPromotionApplicationManager instance;

	public static HotelPromotionApplicationManager launch(HotelPromotionInfoService infoService,
			OrderRelatedInfoHelper helper) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionApplicationManager(infoService, helper);
	}

	public static HotelPromotionApplicationManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param infoService
	 * @param helper
	 */
	private HotelPromotionApplicationManager(HotelPromotionInfoService infoService, OrderRelatedInfoHelper helper) {
		super();
		this.infoService = infoService;
	}

	private HotelPromotionInfoService infoService;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		final OrderInfo tmp = info;
		final Set<PromotionVo> available = infoService.getUserAvailableHotelPromotions(info.getHotel().getId()).stream()
				.filter(vo -> vo.getPromotion().canApplyTo(tmp)).collect(Collectors.toSet());

		for (PromotionVo promotion : available)
			info = promotion.getPromotion().applyTo(info);

		return new OrderVoBuilder(info).setPromotions(available).getOrderInfo();
	}
}
