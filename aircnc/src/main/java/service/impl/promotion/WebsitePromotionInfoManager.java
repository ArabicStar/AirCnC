package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import service.promotion.PromotionContainer;
import service.promotion.WebsitePromotionInfoService;
import vo.promotion.PromotionVo;

public class WebsitePromotionInfoManager implements WebsitePromotionInfoService {
	/* Singleton */
	private static WebsitePromotionInfoManager instance;

	public static WebsitePromotionInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionInfoManager();
	}

	public static WebsitePromotionInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private WebsitePromotionInfoManager() {

	}

	private PromotionContainer container;

	@Override
	public void refreshBuffer() {
		container.refreshContainer();
	}

	@Override
	public Set<PromotionVo> getUserAvailablePromotions() {
		return container.getPractical();
	}
}
