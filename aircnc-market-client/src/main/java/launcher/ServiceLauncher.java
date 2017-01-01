package launcher;

import data.dao.market.MarketDaoProxy;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.member.MemberDaoProxy;
import data.dao.order.OrderDao;
import data.dao.order.OrderDaoProxy;
import data.dao.promotion.PromotionDaoProxy;
import data.dao.promotion.WebsitePromotionDao;
import data.dao.query.PromotionQueryDao;
import data.dao.query.QueryDaoProxy;
import service.impl.market.MarketAccountManager;
import service.impl.market.MarketServiceManager;
import service.impl.member.MemberCreditManager;
import service.impl.member.MemberInfoManager;
import service.impl.order.OrderOperationManager;
import service.impl.promotion.WebsitePromotionManagementManager;
import service.impl.query.CreditQueryManager;
import service.impl.query.HotelQueryManager;
import service.impl.query.OrderQueryManager;
import service.market.MarketAccountService;
import service.market.MarketService;
import service.market.MarketServiceProxy;
import service.member.MemberCreditService;
import service.member.MemberServiceProxy;
import service.order.OrderOperationService;
import service.order.OrderServiceProxy;
import service.promotion.PromotionServiceProxy;
import service.promotion.WebsitePromotionManagementService;
import service.query.CreditQueryService;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import service.query.OrderQueryService;
import service.query.QueryServiceProxy;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			QueryServiceProxy.launch(clientId);
			MemberServiceProxy.launch(clientId);
			PromotionServiceProxy.launch(clientId);
			OrderServiceProxy.launch(clientId);
			MarketServiceProxy.launch(clientId);
			
			launchMemberSerivce(clientId);
			launchQueryService(clientId);
			launchPromotionService(clientId);
			launchOrderService(clientId);
			launchMarketService(clientId);
			

			Log.i("Service launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Service launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}
	
	private static void launchMemberSerivce(Client clientId) {
		final CreditDao creditDao = MemberDaoProxy.getInstance();
		final MemberDao memberDao = MemberDaoProxy.getInstance();

		MemberServiceProxy proxy = MemberServiceProxy.getInstance();

		final MemberCreditService creditService = MemberCreditManager.launch(memberDao, creditDao);

		proxy.loadCreditService(creditService);
	}
	
	private static final void launchOrderService(Client clientId) {

		final OrderDao dao = OrderDaoProxy.getInstance();
		final HotelQueryService hotelQuery = QueryServiceProxy.getInstance();
		final MemberQueryService memberQuery = QueryServiceProxy.getInstance();
		final MemberCreditService creditService = MemberServiceProxy.getInstance();

		OrderServiceProxy proxy = OrderServiceProxy.getInstance();

		OrderOperationService orderOperation = OrderOperationManager.launch(dao, hotelQuery, memberQuery, creditService,
				null);

		proxy.loadOrderOperationService(orderOperation);

	}
	
	private static final void launchPromotionService(Client clientId) {

		final PromotionQueryDao queryDao = QueryDaoProxy.getInstance();
		final WebsitePromotionDao promotionDao = PromotionDaoProxy.getInstance();

		PromotionServiceProxy proxy = PromotionServiceProxy.getInstance();
		final WebsitePromotionManagementService websitePromotion = WebsitePromotionManagementManager.launch(queryDao,
				promotionDao);

		proxy.loadWebsitePromotionManagementService(websitePromotion);

	}

	private static final void launchQueryService(Client clientId) {
		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();
		final MemberDaoProxy memberDao = MemberDaoProxy.getInstance();

		QueryServiceProxy queryProxy = QueryServiceProxy.getInstance();

		final OrderQueryService order = OrderQueryManager.launch(queryDao);
		final HotelQueryService hotel = HotelQueryManager.launch(queryDao);
		final CreditQueryService credit = CreditQueryManager.launch(queryDao);

		queryProxy.loadOrderQueryService(order);
		queryProxy.loadHotelQueryService(hotel);
		queryProxy.loadCreditQueryService(credit);
		
		final MemberQueryService member = MemberInfoManager.launch(memberDao,null,credit,order);
		queryProxy.loadMemberQueryService(member);

	}


	private static void launchMarketService(Client clientId) {
		final MarketDaoProxy marketDao = MarketDaoProxy.getInstance();
		
		final OrderQueryService order = OrderQueryManager.getInstance();
		final MemberCreditService creditService = MemberCreditManager.getInstance();

		MarketServiceProxy marketProxy = MarketServiceProxy.getInstance();

		final MarketAccountService acc = MarketAccountManager.launch(marketDao);
		final MarketService ms = MarketServiceManager.launch(marketDao,order, creditService);

		marketProxy.loadAccountService(acc);
		marketProxy.loadMarketService(ms);
	}
}
