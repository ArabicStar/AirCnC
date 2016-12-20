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
		this.helper = helper;
	}

	private HotelPromotionInfoService infoService;
	private OrderRelatedInfoHelper helper;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		final OrderInfo tmp = info;
		final Set<Promotion> available = infoService.getUserAvailableHotelPromotions(info.getHotelId()).stream()
				.map(vo -> vo.getPromotion()).filter(p -> p.canApplyTo(tmp, helper)).collect(Collectors.toSet());

		for (Promotion promotion : available)
			info = promotion.applyTo(info);

		return new OrderVoBuilder(info).setPromotions(available).getOrderInfo();
	}
}
