package launcher;

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
			launchMemberDao(clientId, helper);

			// launch query dao proxy
			launchQueryDao(clientId, helper);

			Log.i("Dao launch succeed");
		} catch (Exception e) {
			Log.e("Dao launch failed", e);
		}
	}

	private static final void launchMemberDao(Client clientId, RemoteHelper helper) {
		MemberDaoProxy proxy = MemberDaoProxy.launch(clientId);

		proxy.loadRemoteMemberDao(helper.getRemoteMemberDao());
		proxy.loadRemoteCreditDao(helper.getRemoteCreditDao());

		Log.d("member dao launch");
	}

	private static final void launchQueryDao(Client clientId, RemoteHelper helper) {
		QueryDaoProxy proxy = QueryDaoProxy.launch(clientId);

		proxy.loadRemoteCreditQueryDao(helper.getRemoteCreditQueryDao());
	
		Log.d("query dao launch");
	}

	private DaoLauncher() {
	}
}
