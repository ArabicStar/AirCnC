package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import data.dao.promotion.WebsitePromotionDao;
import data.dao.query.PromotionQueryDao;
import po.promotion.PromotionPoBuilder;
import po.promotion.WebsitePromotionPo;
import service.promotion.WebsitePromotionManagementService;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;
import vo.promotion.WebsitePromotionVo;

public class WebsitePromotionManagementManager implements WebsitePromotionManagementService {
	/* Singleton */
	private static WebsitePromotionManagementManager instance;

	public static WebsitePromotionManagementManager launch(PromotionQueryDao query, WebsitePromotionDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionManagementManager(query, dao);
	}

	public static WebsitePromotionManagementManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	public WebsitePromotionManagementManager(PromotionQueryDao query, WebsitePromotionDao dao) {
		super();
		this.query = query;
		this.dao = dao;
	}

	private PromotionQueryDao query;
	private WebsitePromotionDao dao;

	@Override
	public boolean addWebsitePromotion(WebsitePromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Website promotion vo");

		return dao.addWebsitePromotion((WebsitePromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public boolean deleteWebsitePromotion(WebsitePromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Website promotion vo");

		return dao.deleteWebsitePromotion(vo.getId());
	}

	@Override
	public boolean updateWebsitePromotion(WebsitePromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Website promotion vo");

		return dao.updateWebsitePromotion((WebsitePromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public Set<PromotionVo> getWebsiteAllPromotions() {
		return query.getWebsiteAllPromotions().stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<PromotionVo> getWebsiteActivePromotion() {
		return getWebsiteAllPromotions().stream().filter(po -> po.getActive()).collect(Collectors.toSet());
	}
}
