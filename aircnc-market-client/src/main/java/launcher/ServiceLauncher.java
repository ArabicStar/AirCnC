package launcher;

import data.dao.market.MarketDaoProxy;
import data.dao.promotion.PromotionDaoProxy;
import data.dao.query.QueryDaoProxy;
import service.impl.market.MarketAccountManager;
import service.impl.market.MarketInfoManager;
import service.impl.promotion.WebsitePromotionManagementManager;
import service.impl.query.OrderQueryManager;
import service.market.MarketAccountService;
import service.market.MarketInfoService;
import service.market.MarketServiceProxy;
import service.promotion.PromotionServiceProxy;
import service.promotion.WebsitePromotionManagementService;
import service.query.CommentQueryService;
import service.query.OrderQueryService;
import service.query.QueryServiceProxy;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			launchQueryService(clientId);
			launchPromotionService(clientId);
			// launchOrderService(clientId);
			launchMarketService(clientId);

			Log.i("Service launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Service launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}
	
	private static final void launchPromotionService(Client clientId) {

		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();
		final PromotionDaoProxy promotionDao = PromotionDaoProxy.getInstance();
		
		PromotionServiceProxy proxy = PromotionServiceProxy.launch(clientId);
		final WebsitePromotionManagementService websitePromotion = WebsitePromotionManagementManager.launch(queryDao,promotionDao);	

		proxy.loadWebsitePromotionManagementService(websitePromotion);

	}

	private static final void launchQueryService(Client clientId) {
		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();

		QueryServiceProxy proxy = QueryServiceProxy.launch(clientId);

		final OrderQueryService order = OrderQueryManager.launch(queryDao);

		proxy.loadOrderQueryService(order);
	}

	private static void launchMarketService(Client clientId) {
		final MarketDaoProxy marketDao = MarketDaoProxy.getInstance();

		MarketServiceProxy marketProxy = MarketServiceProxy.launch(clientId);

		final MarketAccountService acc = MarketAccountManager.launch(marketDao);

		marketProxy.loadAccountService(acc);
	}
}
