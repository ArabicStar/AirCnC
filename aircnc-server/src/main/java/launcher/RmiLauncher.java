package launcher;

import java.rmi.RemoteException;

import rmi.remote.MemberDaoRemoteObj;
import rmi.remote.PromotionDaoRemoteObj;
import rmi.remote.QueryDaoRemoteObj;
import utils.logger.Log;

public class RmiLauncher {
	public static final void launch() {
		try {
			MemberDaoRemoteObj.launch();
			QueryDaoRemoteObj.launch();
			PromotionDaoRemoteObj.launch();

			Log.i("Rmi launched");
		} catch (RemoteException e) {
			// Fatal error, exit forcely
			Log.f("FATAL - Rmi bind failed, system exits", e);
			System.exit(1);
		}
	}

	private RmiLauncher() {

	}
}
