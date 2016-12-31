package launcher;

import javafx.application.Application;
import presentation.market.CenterController;
import presentation.market.accessor.impl.MarketChargeAccessorImpl;
import presentation.market.accessor.impl.MarketLoginAccessorImpl;
import presentation.market.accessor.impl.MarketPromotionAccessorImpl;
import presentation.market.manager.impl.MarketPromotionManagerImpl;
import utils.logger.Log;

public class UILauncher {
	public static final void launch() {
		try {
			launchAccessor();
			launchManager();

			Log.i("UI launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - UI launch failed, System exits", e);
			System.exit(1);
		}
		Application.launch(CenterController.class);
	}

	private UILauncher() {
	}

	private static final void launchAccessor() {
		MarketPromotionAccessorImpl.launch();
		MarketLoginAccessorImpl.launch();
		MarketChargeAccessorImpl.launch();

	}

	private static final void launchManager() {
		
		MarketPromotionManagerImpl.launch();

	}
}
