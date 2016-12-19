package launcher;

import data.dao.member.MemberDaoProxy;
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

			// launch member dao proxy
			launchMemberDao(helper);

			// launch query dao proxy
			launchQueryDao(helper);

			// launch promotin dao proxy
			launchPromotionDao(helper);
			Log.i("Dao launch succeed");
		} catch (Exception e) {
			Log.e("Dao launch failed", e);
		}
	}

	private static void launchPromotionDao(RemoteHelper helper) {
		PromotionDaoProxy proxy = PromotionDaoProxy.launch();

		proxy.loadRemoteHotelDao(helper.getRemoteHotelPromotionDao());
		proxy.loadRemoteWebsiteDao(helper.getRemoteWebsitePromotionDao());

		Log.d("promotion dao launched");
	}

	private static final void launchMemberDao(RemoteHelper helper) {
		MemberDaoProxy proxy = MemberDaoProxy.launch();

		proxy.loadRemoteMemberDao(helper.getRemoteMemberDao());
		proxy.loadRemoteCreditDao(helper.getRemoteCreditDao());

		Log.d("member dao launched");
	}

	private static final void launchQueryDao(RemoteHelper helper) {
		QueryDaoProxy proxy = QueryDaoProxy.launch();

		proxy.loadRemoteCreditQueryDao(helper.getRemoteCreditQueryDao());

		Log.d("query dao launched");
	}

	private DaoLauncher() {
	}
}
