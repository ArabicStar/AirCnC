package launcher;

import java.rmi.RemoteException;

import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.impl.member.MemberDaoImpl;
import data.dao.impl.query.PromotionQueryDaoImpl;
import data.dao.query.PromotionQueryDao;
import rmi.remote.WebsitePromotionApplicationRemoteObj;
import rmi.remote.WebsitePromotionInfoRemoteObj;
import service.impl.promotion.OrderRelateInfoManager;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.promotion.WebsitePromotionInfoService;
import utils.logger.Log;

public class ServiceLauncher {
	public static final void launch() {
		try {
			launchWebsitePromotionInfoService();
			launchOrderRelatedInfoHelper();
			launchWebsitePromotionApplicationService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		Log.i("Services launched");
	}

	private static void launchOrderRelatedInfoHelper() {
		final MemberDaoImpl memberQuery = MemberDaoImpl.INSTANCE;
		final HotelDaoImpl hotelQuery = HotelDaoImpl.INSTANCE;

		OrderRelateInfoManager.launch(memberQuery, hotelQuery);

		Log.d("Order Related Information Helper launched");
	}

	private static final void launchWebsitePromotionInfoService() throws RemoteException {
		final PromotionQueryDao queryDao = PromotionQueryDaoImpl.INSTANCE;

		WebsitePromotionInfoManager.launch(queryDao);

		WebsitePromotionInfoRemoteObj.launch();

		Log.d("Website Promotion Information Service launched");
	}

	private static final void launchWebsitePromotionApplicationService() throws RemoteException {
		final WebsitePromotionInfoService infoService = WebsitePromotionInfoManager.getInstance();
		final OrderRelateInfoManager helper = OrderRelateInfoManager.getInstance();

		WebsitePromotionApplicationManager.launch(infoService, helper);

		WebsitePromotionApplicationRemoteObj.launch();
		Log.d("Website Promotion Application Service launched");
	}

	private ServiceLauncher() {
	}

}
