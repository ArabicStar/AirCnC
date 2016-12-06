package launcher;

import rmi.RemoteHelper;
import utils.logger.Log;

public class RmiLauncher {
	public static final void launch() {
		try {
			RemoteHelper.launch();
			Log.i("Rmi launch succeed");
		} catch (Exception e) {
			Log.e("Rmi launch failed", e);
		}
	}

	private RmiLauncher() {
	}
}
