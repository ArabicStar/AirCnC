package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import service.promotion.WebsitePromotionApplicationService;
import service.promotion.WebsitePromotionInfoService;
import utils.info.order.OrderInfo;
import vo.order.OrderVoBuilder;
import vo.promotion.PromotionVo;

public class WebsitePromotionApplicationManager implements WebsitePromotionApplicationService {
	/* Singleton */
	private static WebsitePromotionApplicationManager instance;

	public static WebsitePromotionApplicationManager launch(WebsitePromotionInfoService infoService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionApplicationManager(infoService);
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
	private WebsitePromotionApplicationManager(WebsitePromotionInfoService infoService) {
		super();
		this.infoService = infoService;
	}

	private WebsitePromotionInfoService infoService;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) {
		final OrderInfo tmp = info;
		final Set<PromotionVo> available = infoService.getUserAvailableWebsitePromotions().stream()
				.filter(vo -> vo.getPromotion().canApplyTo(tmp)).collect(Collectors.toSet());

		for (PromotionVo promotion : available)
			info = promotion.getPromotion().applyTo(info);

		return new OrderVoBuilder(info).setPromotions(available).getOrderInfo();
	}

}
