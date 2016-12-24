package launcher;

import javafx.application.Application;
import presentation.manage.CenterController;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.manager.impl.HotelManagePromotionManagerImpl;
import presentation.manage.manager.impl.ManageHotelCommentManagerImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
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
		HotelManageInfoManagerImpl.launch();
		HotelManagePromotionManagerImpl.launch();
		ManageHotelCommentManagerImpl.launch();
		MarketManageInfoManagerImpl.launch();
		MemberManageInfoImpl.launch();
		
		HotelManageInfoAccessorImpl.launch();
		MarketManageInfoAccessorImpl.launch();
		MemberManageInfoAccessorImpl.launch();
	}
}
