package launcher;

import data.dao.market.MarketDaoProxy;
import data.dao.member.MemberDaoProxy;
import data.dao.order.OrderDaoProxy;
import data.dao.promotion.PromotionDaoProxy;
import data.dao.query.QueryDaoProxy;
import rmi.RemoteHelper;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class DaoLauncher {
	public static final void launch(Client clientId) {
		try {
			// get remote helper instance
			final RemoteHelper helper = RemoteHelper.getInstance();

			// launch query dao proxy
			launchQueryDao(helper);

			// launch promotin dao proxy
			launchPromotionDao(helper);
			
			launchMarketDao(helper);
			
			// launch member dao proxy
			launchMemberDao(helper);
			// launch order dao proxy
			launchOrderDao(helper);
			Log.i("Dao launch succeed");
		} catch (Exception e) {
			Log.e("Dao launch failed", e);
		}
	}
	
	private static void launchOrderDao(RemoteHelper helper) {
		OrderDaoProxy proxy = OrderDaoProxy.launch();

		proxy.loadRemoteOrderDao(helper.getRemoteOrderDao());
	}

	private static void launchPromotionDao(RemoteHelper helper) {
		PromotionDaoProxy proxy = PromotionDaoProxy.launch();

		proxy.loadRemoteWebsiteDao(helper.getRemoteWebsitePromotionDao());

		Log.d("promotion dao launched");
	}
	
	private static void launchMarketDao(RemoteHelper helper) {
		MarketDaoProxy proxy = MarketDaoProxy.launch();

		proxy.loadRemoteMarketDao(helper.getRemoteMarketDao());

		Log.d("market dao launched");
	}
	
	private static void launchMemberDao(RemoteHelper helper) {
		MemberDaoProxy proxy = MemberDaoProxy.launch();

		proxy.loadRemoteMemberDao(helper.getRemoteMemberDao());
		proxy.loadRemoteCreditDao(helper.getRemoteCreditDao());
	}


	private static final void launchQueryDao(RemoteHelper helper) {
		QueryDaoProxy proxy = QueryDaoProxy.launch();

		proxy.loadRemoteHotelQueryDao(helper.getRemoteHotelQueryDao());
		proxy.loadRemotePromotionQueryDao(helper.getRemotePromotionQueryDao());
		proxy.loadRemoteOrderQueryDao(helper.getRemoteOrderQueryDao());

		Log.d("query dao launched");
	}


	private DaoLauncher() {
	}
}
