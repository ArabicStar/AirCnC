package launcher;

import java.rmi.RemoteException;

import rmi.remote.HotelDaoRemoteObj;
import rmi.remote.MarketDaoRemoteObj;
import rmi.remote.MemberDaoRemoteObj;
import rmi.remote.OrderDaoRemoteObj;
import rmi.remote.PromotionDaoRemoteObj;
import rmi.remote.QueryDaoRemoteObj;
import utils.logger.Log;

public class RmiLauncher {
	public static final void launch() {
		try {
			MemberDaoRemoteObj.launch();
			HotelDaoRemoteObj.launch();
			MarketDaoRemoteObj.launch();
			QueryDaoRemoteObj.launch();
			PromotionDaoRemoteObj.launch();
			OrderDaoRemoteObj.launch();
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
