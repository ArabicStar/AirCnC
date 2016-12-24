package launcher;

import javafx.application.Application;
import presentation.market.CenterController;
import utils.logger.Log;

public class UILauncher {
	public static final void launch() {
		try {
			launchManageUI();

			Log.i("UI launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - UI launch failed, System exits", e);
			System.exit(1);
		}
		Application.launch(CenterController.class);
	}

	private UILauncher() {
	}

	private static final void launchManageUI(){
		
	}
}
