package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import data.dao.query.PromotionQueryDao;
import service.promotion.PromotionContainer;
import service.promotion.WebsitePromotionInfoService;
import vo.promotion.PromotionVo;

public class WebsitePromotionInfoManager implements WebsitePromotionInfoService {
	/* Singleton */
	private static WebsitePromotionInfoManager instance;

	public static WebsitePromotionInfoManager launch(PromotionQueryDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionInfoManager(dao);
	}

	public static WebsitePromotionInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private WebsitePromotionInfoManager(PromotionQueryDao dao) {
		this.container = new WebsitePromotionContainer(dao);
	}

	private PromotionContainer container;

	@Override
	public void refreshBuffer() {
		container.refreshContainer();
	}

	@Override
	public Set<PromotionVo> getUserAvailableWebsitePromotions() {
		return container.getPractical();
	}

	@Override
	public PromotionVo getWebsitePromotion(long id) {
		return container.getPromotion(id);
	}
}
