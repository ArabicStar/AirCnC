package launcher;

import data.dao.member.MemberDaoProxy;
import data.dao.query.QueryDaoProxy;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberCreditManager;
import service.impl.promotion.HotelPromotionApplicationManager;
import service.impl.promotion.HotelPromotionInfoManager;
import service.impl.promotion.OrderRelateInfoManager;
import service.impl.promotion.PromotionApplicationManager;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.member.MemberAccountService;
import service.member.MemberCreditService;
import service.member.MemberServiceProxy;
import service.promotion.HotelPromotionApplicationService;
import service.promotion.HotelPromotionInfoService;
import service.promotion.PromotionApplicationService;
import service.promotion.PromotionServiceProxy;
import service.promotion.WebsitePromotionApplicationService;
import service.promotion.WebsitePromotionInfoService;
import utils.logger.Log;
import utils.promotion.OrderRelatedInfoHelper;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			launchMemberService(clientId);

			launchPromotionService(clientId);

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

		proxy.loadAccountService(acc);
		proxy.loadCreditService(cre);
	}

	private static final void launchPromotionService(Client clientId) {
		final MemberDaoProxy memberDaoProxy = MemberDaoProxy.getInstance();
		final QueryDaoProxy queryDaoProxy = QueryDaoProxy.getInstance();

		final OrderRelatedInfoHelper helper = OrderRelateInfoManager.launch(memberDaoProxy, queryDaoProxy);
		final HotelPromotionInfoService hotelInfo = HotelPromotionInfoManager.launch(queryDaoProxy);
		final WebsitePromotionInfoService websiteInfo = WebsitePromotionInfoManager.launch(queryDaoProxy);
		final HotelPromotionApplicationService hotelApplication = HotelPromotionApplicationManager.launch(hotelInfo,
				helper);
		final WebsitePromotionApplicationService websiteApplication = WebsitePromotionApplicationManager
				.launch(websiteInfo, helper);
		final PromotionApplicationService application = PromotionApplicationManager.launch(hotelApplication,
				websiteApplication);

		PromotionServiceProxy proxy = PromotionServiceProxy.launch(clientId);

		proxy.loadHotelPromotionInfoService(hotelInfo);
		proxy.loadWebsitePromotionInfoService(websiteInfo);
		proxy.loadPromotionApplicationService(application);
	}

}
