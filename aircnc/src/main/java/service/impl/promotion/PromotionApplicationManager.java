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

	public static PromotionApplicationManager launch(HotelPromotionApplicationService hotel,
			WebsitePromotionApplicationService website) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new PromotionApplicationManager(hotel, website);
	}

	public static PromotionApplicationManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private HotelPromotionApplicationService hotel;

	/**
	 * @param hotel
	 * @param website
	 */
	private PromotionApplicationManager(HotelPromotionApplicationService hotel,
			WebsitePromotionApplicationService website) {
		super();
		this.hotel = hotel;
		this.website = website;
	}

	private WebsitePromotionApplicationService website;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		return website.applyPromotion(hotel.applyPromotion(info));
	}

}
