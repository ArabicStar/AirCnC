package launcher;

import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			launchMarketService(clientId);

			Log.i("Service launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Service launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}
	
	private static final void launchMarketService(Client clientId) {
	}
}
