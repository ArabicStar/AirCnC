package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import service.promotion.PromotionApplicationService;
import service.promotion.WebsitePromotionInfoService;
import utils.info.order.OrderInfo;
import utils.promotion.OrderRelatedInfoHelper;
import utils.promotion.Promotion;
import vo.order.OrderVoBuilder;

public class WebsitePromotionApplicationManager implements PromotionApplicationService {
	/* Singleton */
	private static WebsitePromotionApplicationManager instance;

	public static WebsitePromotionApplicationManager launch(WebsitePromotionInfoService infoService,
			OrderRelatedInfoHelper helper) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionApplicationManager(infoService, helper);
	}

	public static WebsitePromotionApplicationManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param infoService
	 * @param helper
	 */
	private WebsitePromotionApplicationManager(WebsitePromotionInfoService infoService, OrderRelatedInfoHelper helper) {
		super();
		this.infoService = infoService;
		this.helper = helper;
	}

	private WebsitePromotionInfoService infoService;
	private OrderRelatedInfoHelper helper;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		final OrderInfo tmp = info;
		final Set<Promotion> available = infoService.getUserAvailablePromotions().stream().map(vo -> vo.getPromotion())
				.filter(p -> p.canApplyTo(tmp, helper)).collect(Collectors.toSet());

		for (Promotion promotion : available)
			info = promotion.applyTo(info);

		return new OrderVoBuilder(info).setPromotions(available).getOrderInfo();
	}

}
