package launcher;

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

			Log.i("Dao launch succeed");
		} catch (Exception e) {
			Log.e("Dao launch failed", e);
		}
	}

	private static void launchPromotionDao(RemoteHelper helper) {
		PromotionDaoProxy proxy = PromotionDaoProxy.launch();

		proxy.loadRemoteWebsiteDao(helper.getRemoteWebsitePromotionDao());

		Log.d("promotion dao launched");
	}


	private static final void launchQueryDao(RemoteHelper helper) {
		QueryDaoProxy proxy = QueryDaoProxy.launch();

//		proxy.loadRemoteCommentQueryDao(helper.);
		proxy.loadRemotePromotionQueryDao(helper.getRemotePromotionQueryDao());
//		proxy.loadRemoteOrderQueryDao(helper.get);

		Log.d("query dao launched");
	}


	private DaoLauncher() {
	}
}
