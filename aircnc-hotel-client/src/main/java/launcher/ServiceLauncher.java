package launcher;

import data.dao.hotel.HotelDao;
import data.dao.hotel.HotelDaoProxy;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.member.MemberDaoProxy;
import data.dao.order.OrderDao;
import data.dao.order.OrderDaoProxy;
import data.dao.promotion.HotelPromotionDao;
import data.dao.promotion.PromotionDaoProxy;
import data.dao.query.CommentQueryDao;
import data.dao.query.HotelQueryDao;
import data.dao.query.OrderQueryDao;
import data.dao.query.PromotionQueryDao;
import data.dao.query.QueryDaoProxy;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.hotel.HotelOrderService;
import service.hotel.HotelServiceProxy;
import service.impl.hotel.HotelAccountManager;
import service.impl.hotel.HotelInfoManager;
import service.impl.hotel.HotelOrderManager;
import service.impl.member.MemberCreditManager;
import service.impl.member.MemberInfoManager;
import service.impl.order.OrderOperationManager;
import service.impl.promotion.HotelPromotionManagementManager;
import service.impl.query.CommentQueryManager;
import service.impl.query.HotelQueryManager;
import service.impl.query.OrderQueryManager;
import service.member.MemberCreditService;
import service.member.MemberServiceProxy;
import service.order.OrderOperationService;
import service.order.OrderServiceProxy;
import service.promotion.HotelPromotionManagementService;
import service.promotion.PromotionServiceProxy;
import service.query.CommentQueryService;
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
			HotelServiceProxy.launch(clientId);

			launchQueryService(clientId);
			launchMemberSerivce(clientId);
			launchPromotionService(clientId);
			launchOrderService(clientId);
			launchHotelService(clientId);
			Log.i("Services launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Services launch failed, System exits", e);
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

	private static final void launchPromotionService(Client clientId) {

		final PromotionQueryDao queryDao = QueryDaoProxy.getInstance();
		final HotelPromotionDao promotionDao = PromotionDaoProxy.getInstance();

		PromotionServiceProxy proxy = PromotionServiceProxy.getInstance();
		final HotelPromotionManagementService hotelPromotion = HotelPromotionManagementManager.launch(queryDao,
				promotionDao);

		proxy.loadHotelPromotionManagementService(hotelPromotion);
		// final WebsitePromotionInfoService websiteInfo =
		// WebsitePromotionInfoManager.launch(queryDaoProxy);
		// final HotelPromotionApplicationService hotelApplication =
		// HotelPromotionApplicationManager.launch(hotelInfo);
		// final WebsitePromotionApplicationService websiteApplication =
		// WebsitePromotionApplicationManager
		// .launch(websiteInfo);
		// final PromotionApplicationService application =
		// PromotionApplicationManager.launch(hotelApplication,
		// websiteApplication);
		// proxy.loadWebsitePromotionInfoService(websiteInfo);
		// proxy.loadPromotionApplicationService(application);
	}

	private static final void launchQueryService(Client clientId) {
		final OrderQueryDao orderQueryDao = QueryDaoProxy.getInstance();
		final HotelQueryDao hotelQueryDao = QueryDaoProxy.getInstance();
		final CommentQueryDao commentQuery = QueryDaoProxy.getInstance();
		final MemberDao memberDao = MemberDaoProxy.getInstance();

		QueryServiceProxy proxy = QueryServiceProxy.getInstance();

		final MemberQueryService memberQuery = MemberInfoManager.launch(memberDao, null, null, null);
		final OrderQueryService order = OrderQueryManager.launch(orderQueryDao);
		final CommentQueryService comm = CommentQueryManager.launch(commentQuery);
		final HotelQueryService hotelQuery = HotelQueryManager.launch(hotelQueryDao);

		proxy.loadOrderQueryService(order);
		proxy.loadCommentQueryService(comm);
		proxy.loadMemberQueryService(memberQuery);
		proxy.loadHotelQueryService(hotelQuery);
	}

	private static void launchHotelService(Client clientId) {
		final HotelDao hotelDao = HotelDaoProxy.getInstance();
		final CommentQueryService queryProxy = QueryServiceProxy.getInstance();
		final OrderQueryService orderQuery = QueryServiceProxy.getInstance();
		final OrderOperationService orderOperation = OrderServiceProxy.getInstance();
		final HotelPromotionManagementService promotionManagement = PromotionServiceProxy.getInstance();

		HotelServiceProxy hotelProxy = HotelServiceProxy.getInstance();

		final HotelAccountService acc = HotelAccountManager.launch(hotelDao);
		final HotelOrderService order = HotelOrderManager.launch(hotelDao, acc, orderQuery, orderOperation);
		final HotelInfoService info = HotelInfoManager.launch(hotelDao, acc, promotionManagement, queryProxy);

		hotelProxy.loadAccountService(acc);
		hotelProxy.loadInfoService(info);
		hotelProxy.loadHotelOrderService(order);
	}

	private static void launchOrderService(Client clientId) {
		final OrderDao dao = OrderDaoProxy.getInstance();
		final HotelQueryService hotelQuery = QueryServiceProxy.getInstance();
		final MemberQueryService memberQuery = QueryServiceProxy.getInstance();
		final MemberCreditService creditService = MemberServiceProxy.getInstance();

		OrderServiceProxy proxy = OrderServiceProxy.getInstance();

		OrderOperationService orderOperation = OrderOperationManager.launch(dao, hotelQuery, memberQuery, creditService,
				null);

		proxy.loadOrderOperationService(orderOperation);

	}
}
