package launcher;

import data.dao.impl.query.PromotionQueryDaoImpl;
import data.dao.query.PromotionQueryDao;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.promotion.WebsitePromotionInfoService;
import utils.logger.Log;

public class ServiceLauncher {
	public static final void launch() {
		launchWebsitePromotionService();
		launchWebsitePromotionApplicationService();
	}

	private static final void launchWebsitePromotionService() {
		final PromotionQueryDao queryDao = PromotionQueryDaoImpl.INSTANCE;

		WebsitePromotionInfoManager.launch(queryDao);

		Log.d("Website Promotion Information Service launched");
	}

	private static final void launchWebsitePromotionApplicationService() {
		final WebsitePromotionInfoService infoService = WebsitePromotionInfoManager.getInstance();

		WebsitePromotionApplicationManager.launch(infoService, null);

		Log.d("Website Promotion Application Service launched");
	}

	private ServiceLauncher() {
	}

}
