package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import service.promotion.HotelPromotionApplicationService;
import service.promotion.PromotionApplicationService;
import service.promotion.WebsitePromotionApplicationService;
import utils.info.order.OrderInfo;

public class PromotionApplicationManager implements PromotionApplicationService {
	/* Singleton */
	private static PromotionApplicationManager instance;

	public static PromotionApplicationManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new PromotionApplicationManager();
	}

	public static PromotionApplicationManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private PromotionApplicationManager() {

	}

	private HotelPromotionApplicationService hotel;
	private WebsitePromotionApplicationService website;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		return website.applyPromotion(hotel.applyPromotion(info));
	}

}
