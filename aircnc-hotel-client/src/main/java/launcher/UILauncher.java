package launcher;

import javafx.application.Application;
import presentation.hotel.CenterController;
import presentation.hotel.accessor.impl.HotelLoginAccessorImpl;
import presentation.hotel.accessor.impl.HotelPromotionAccessorImpl;
import presentation.hotel.accessor.impl.HotelRoomAccessorImpl;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.HotelCommentManagerImpl;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import presentation.hotel.manager.impl.HotelPromotionManagerImpl;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.manager.impl.InfoManagerImpl;
import utils.logger.Log;

public class UILauncher {
	public static final void launch() {
		try {
			launchAccessor();
			launchManager();

			Application.launch(CenterController.class);

			Log.i("Hotel UI launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Hotel UI launch failed, System exits", e);
			System.exit(1);
		}
	}

	private static final void launchAccessor() {
		InfoModifyAccessorImpl.launch();
		HotelRoomAccessorImpl.launch();
		HotelPromotionAccessorImpl.launch();
		SearchOrderAccessorImpl.launch();
		HotelLoginAccessorImpl.launch();

	}

	private static final void launchManager() {
		HotelOrderManagerImpl.launch();
		HotelCommentManagerImpl.launch();
		HotelPromotionManagerImpl.launch();
		InfoManagerImpl.launch();
		HotelRoomManagerImpl.launch();
	}

	private UILauncher() {

	}
}
