package launcher;

import data.dao.hotel.HotelDaoProxy;
import data.dao.market.MarketDaoProxy;
import data.dao.member.MemberDaoProxy;
import data.dao.query.QueryDaoProxy;
import rmi.RemoteHelper;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class DaoLauncher {

	public static final void launch(Client clientId) {
		try {
			// get remote helper instance
			final RemoteHelper helper = RemoteHelper.getInstance();

			// launch member dao proxy
			launchMemberDao(helper);

			// launch hotel dao proxy
			launchHotelDao(helper);

			// launch market dao proxy
			launchMarketDao(helper);

			// launch query dao proxy
			launchQueryDao(helper);

			Log.i("Dao launch succeed");
		} catch (Exception e) {
			Log.e("Dao launch failed", e);
		}
	}

	private static final void launchMemberDao(RemoteHelper helper) {
		MemberDaoProxy proxy = MemberDaoProxy.launch();

		proxy.loadRemoteMemberDao(helper.getRemoteMemberDao());

		Log.d("member dao launched");
	}

	private static final void launchHotelDao(RemoteHelper helper) {
		HotelDaoProxy proxy = HotelDaoProxy.launch();

		proxy.loadRemoteHotelDao(helper.getRemoteHotelDao());

		Log.d("hotel dao launched");
	}

	private static final void launchMarketDao(RemoteHelper helper) {
		MarketDaoProxy proxy = MarketDaoProxy.launch();

		proxy.loadRemoteMarketDao(helper.getRemoteMarketDao());

		Log.d("market dao launched");
	}

	private static final void launchQueryDao(RemoteHelper helper) {
		QueryDaoProxy proxy = QueryDaoProxy.launch();

		proxy.loadRemoteCommentQueryDao(helper.getRemoteCommentQueryDao());
		proxy.loadRemotePromotionQueryDao(helper.getRemotePromotionQueryDao());

		Log.d("query dao launched");
	}
}
