package data.dao.impl.promotion;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.promotion.WebsitePromotionDao;
import po.promotion.WebsitePromotionPo;

public class WebsitePromotionDaoImpl implements WebsitePromotionDao {
	/* Singleton */
	private static WebsitePromotionDaoImpl instance;

	public static WebsitePromotionDaoImpl launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new WebsitePromotionDaoImpl();
	}

	public static WebsitePromotionDaoImpl getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */
	
	private WebsitePromotionDaoImpl() {

	}

	@Override
	public boolean addWebsitePromotion(WebsitePromotionPo po) {
		if (po == null)
			return false;
		if (po.getId() != 0)
			return false;

		return execute(session -> {
			session.save(po);

			return Boolean.TRUE;
		});
	}

	@Override
	public boolean deleteWebsitePromotion(long id) {
		if (id <= 0)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			WebsitePromotionPo toBeDel = session.get(WebsitePromotionPo.class, id);

			if (flag = Boolean.valueOf(toBeDel != null))
				toBeDel.expire();

			return flag;
		});
	}

	@Override
	public boolean updateWebsitePromotion(WebsitePromotionPo po) {
		if (po == null || po.getId() <= 0)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			WebsitePromotionPo toBeUpdate = session.get(WebsitePromotionPo.class, po.getId());

			if (flag = Boolean.valueOf(toBeUpdate != null)) {
				po.setId(0);

				// save updated po
				session.save(po);

				// "delete" old po: set it inactive
				toBeUpdate.expire();
			}

			return flag;
		});

	}

	@Override
	public WebsitePromotionPo findWebsitePromotion(long id) {
		if (id <= 0)
			return null;

		return execute(session -> {
			return (WebsitePromotionPo) session.get(WebsitePromotionPo.class, id);
		});
	}

}
