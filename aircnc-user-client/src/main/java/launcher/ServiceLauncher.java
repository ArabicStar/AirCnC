package launcher;

import data.dao.member.MemberDaoProxy;
import data.dao.order.OrderDao;
import data.dao.order.OrderDaoProxy;
import data.dao.query.QueryDaoProxy;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberCreditManager;
import service.impl.member.MemberInfoManager;
import service.impl.order.OrderInfoManager;
import service.impl.order.OrderOperationManager;
import service.impl.promotion.HotelPromotionApplicationManager;
import service.impl.promotion.HotelPromotionInfoManager;
import service.impl.promotion.PromotionApplicationManager;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.impl.query.CreditQueryManager;
import service.impl.query.HotelQueryManager;
import service.impl.query.OrderQueryManager;
import service.member.MemberAccountService;
import service.member.MemberCreditService;
import service.member.MemberInfoService;
import service.member.MemberServiceProxy;
import service.order.OrderInfoService;
import service.order.OrderOperationService;
import service.order.OrderServiceProxy;
import service.promotion.HotelPromotionApplicationService;
import service.promotion.HotelPromotionInfoService;
import service.promotion.PromotionApplicationService;
import service.promotion.PromotionServiceProxy;
import service.promotion.WebsitePromotionApplicationService;
import service.promotion.WebsitePromotionInfoService;
import service.query.CommentQueryService;
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
			launchMemberService(clientId);
			launchQueryService(clientId);
			launchPromotionService(clientId);
			launchOrderService(clientId);
			Log.i("Services launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Services launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}

	private static final void launchMemberService(Client clientId) {
		final MemberDaoProxy memberDaoProxy = MemberDaoProxy.getInstance();

		MemberServiceProxy proxy = MemberServiceProxy.launch(clientId);

		final MemberAccountService acc = MemberAccountManager.launch(memberDaoProxy);
		final MemberCreditService cre = MemberCreditManager.launch(memberDaoProxy, memberDaoProxy);
		final MemberInfoService info = MemberInfoManager.launch(memberDaoProxy, acc, null, null);

		proxy.loadAccountService(acc);
		proxy.loadCreditService(cre);
		proxy.loadInfoService(info);
	}

	private static final void launchPromotionService(Client clientId) {
		final QueryDaoProxy queryDaoProxy = QueryDaoProxy.getInstance();

		final HotelPromotionInfoService hotelInfo = HotelPromotionInfoManager.launch(queryDaoProxy);
		final WebsitePromotionInfoService websiteInfo = WebsitePromotionInfoManager.launch(queryDaoProxy);
		final HotelPromotionApplicationService hotelApplication = HotelPromotionApplicationManager.launch(hotelInfo);
		final WebsitePromotionApplicationService websiteApplication = WebsitePromotionApplicationManager
				.launch(websiteInfo);
		final PromotionApplicationService application = PromotionApplicationManager.launch(hotelApplication,
				websiteApplication);

		PromotionServiceProxy proxy = PromotionServiceProxy.launch(clientId);

		proxy.loadHotelPromotionInfoService(hotelInfo);
		proxy.loadWebsitePromotionInfoService(websiteInfo);
		proxy.loadPromotionApplicationService(application);
	}

	private static final void launchQueryService(Client clientId) {
		QueryServiceProxy proxy = QueryServiceProxy.launch(clientId);

		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();

		final MemberQueryService mem = MemberInfoManager.getInstance();
		final CreditQueryService cre = CreditQueryManager.launch(queryDao);
		final HotelQueryService hotel = HotelQueryManager.launch(queryDao);
		final OrderQueryService order = OrderQueryManager.launch(queryDao);
		final CommentQueryService comm = null;

		proxy.loadOrderQueryService(order);
		proxy.loadMemberQueryService(mem);
		proxy.loadHotelQueryService(hotel);
		proxy.loadCreditQueryService(cre);
		proxy.loadCommentQueryService(comm);
	}

	private static void launchOrderService(Client clientId) {
		OrderServiceProxy proxy = OrderServiceProxy.launch(clientId);

		final OrderDao orderDao = OrderDaoProxy.getInstance();

		final HotelQueryService hotelQuery = QueryServiceProxy.getInstance();
		final MemberQueryService memberQuery = QueryServiceProxy.getInstance();
		final MemberCreditService creditService = MemberServiceProxy.getInstance();
		final PromotionApplicationService promotionService = PromotionServiceProxy.getInstance();

		final OrderInfoService info = OrderInfoManager.launch(orderDao);
		final OrderOperationService operation = OrderOperationManager.launch(orderDao, hotelQuery, memberQuery,
				creditService, promotionService);

		proxy.loadOrderInfoService(info);
		proxy.loadOrderOperationService(operation);
	}

}
