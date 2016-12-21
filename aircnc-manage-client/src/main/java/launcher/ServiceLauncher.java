package launcher;

import data.dao.hotel.HotelDaoProxy;
import data.dao.market.MarketDaoProxy;
import data.dao.member.MemberDaoProxy;
import service.impl.manage.ManageHotelManager;
import service.impl.manage.ManageMarketManager;
import service.impl.manage.ManageMemberManager;
import service.manage.ManageHotelService;
import service.manage.ManageMarketService;
import service.manage.ManageMemberService;
import service.manage.ManageServiceProxy;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			launchManageService(clientId);

			Log.i("Services launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Services launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}
	
	private static final void launchManageService(Client clientId) {
		final MemberDaoProxy memberDaoProxy = MemberDaoProxy.getInstance();
		final HotelDaoProxy hotelDaoProxy = HotelDaoProxy.getInstance();
		final MarketDaoProxy marketDaoProxy = MarketDaoProxy.getInstance();

		ManageServiceProxy proxy = ManageServiceProxy.launch(clientId);
		
		final ManageMemberService mem = ManageMemberManager.launch(memberDaoProxy);
		final ManageHotelService hot = ManageHotelManager.launch(hotelDaoProxy);
		final ManageMarketService mar = ManageMarketManager.launch(marketDaoProxy);

		proxy.loadMemberManageService(mem);
		proxy.loadHotelManageService(hot);
		proxy.loadMarketManageService(mar);
	}
}
