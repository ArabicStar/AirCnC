package service.impl.promotion;

import data.dao.query.PromotionQueryDao;

public class WebsitePromotionContainer extends AbstractPromotionContainer {
	private PromotionQueryDao dao;

	public WebsitePromotionContainer(PromotionQueryDao dao) {
		super();
		this.dao = dao;
		refreshContainer();
	}

	@Override
	public void refreshContainer() {
		all.clear();
		put(dao.getWebsiteAllPromotions());
	}

}
