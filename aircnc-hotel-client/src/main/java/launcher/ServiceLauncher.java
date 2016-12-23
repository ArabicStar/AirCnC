package launcher;

import data.dao.hotel.HotelDaoProxy;
import data.dao.member.MemberDaoProxy;
import data.dao.query.QueryDaoProxy;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import service.hotel.HotelServiceProxy;
import service.impl.hotel.HotelAccountManager;
import service.impl.hotel.HotelInfoManager;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberCreditManager;
import service.impl.member.MemberInfoManager;
import service.impl.promotion.HotelPromotionApplicationManager;
import service.impl.promotion.HotelPromotionInfoManager;
import service.impl.promotion.HotelPromotionManagementManager;
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
import service.promotion.HotelPromotionApplicationService;
import service.promotion.HotelPromotionInfoService;
import service.promotion.HotelPromotionManagementService;
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
			launchQueryService(clientId);
			launchPromotionService(clientId);
			// launchOrderService(clientId);
			launchHotelService(clientId);
			Log.i("Services launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Services launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}

	private static final void launchPromotionService(Client clientId) {

		final HotelPromotionManagementService hotelPromotion = HotelPromotionManagementManager.launch();
//		final WebsitePromotionInfoService websiteInfo = WebsitePromotionInfoManager.launch(queryDaoProxy);
//		final HotelPromotionApplicationService hotelApplication = HotelPromotionApplicationManager.launch(hotelInfo);
//		final WebsitePromotionApplicationService websiteApplication = WebsitePromotionApplicationManager
//				.launch(websiteInfo);
//		final PromotionApplicationService application = PromotionApplicationManager.launch(hotelApplication,
//				websiteApplication);

		PromotionServiceProxy proxy = PromotionServiceProxy.launch(clientId);

		proxy.loadHotelPromotionManagementService(hotelPromotion);;
//		proxy.loadWebsitePromotionInfoService(websiteInfo);
//		proxy.loadPromotionApplicationService(application);
	}

	private static final void launchQueryService(Client clientId) {
		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();

		QueryServiceProxy proxy = QueryServiceProxy.launch(clientId);

		final OrderQueryService order = OrderQueryManager.launch(queryDao);
		final CommentQueryService comm = null;

		proxy.loadOrderQueryService(order);
		proxy.loadCommentQueryService(comm);
	}

	private static void launchHotelService(Client clientId) {
		final HotelDaoProxy hotelDao = HotelDaoProxy.getInstance();
		final QueryDaoProxy queryDao = QueryDaoProxy.getInstance();

		HotelServiceProxy hotelProxy = HotelServiceProxy.launch(clientId);
		QueryServiceProxy queryProxy = QueryServiceProxy.getInstance();
		PromotionServiceProxy promotionProxy = PromotionServiceProxy.getInstance();

		final HotelAccountService acc = HotelAccountManager.launch(hotelDao);
		final HotelInfoService info = HotelInfoManager.launch(hotelDao,queryDao, acc, queryProxy, promotionProxy, queryProxy);

		hotelProxy.loadAccountService(acc);
		hotelProxy.loadInfoService(info);

	}

	private static void launchOrderService(Client clientId) {
		// TODO 自动生成的方法存根

	}
}
