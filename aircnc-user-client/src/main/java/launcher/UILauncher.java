package launcher;

import javafx.application.Application;
import presentation.member.CenterController;
import presentation.member.accessor.impl.CreditChangeOrderAccessorImpl;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.accessor.impl.RegisterAccessorImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.HistoryOrderManagerImpl;
import presentation.member.manager.impl.HotelCommentManagerImpl;
import presentation.member.manager.impl.HotelPromotionManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import utils.logger.Log;

public class UILauncher {
	public static final void launch() {
		try {
			launchAccessor();
			launchManager();

			Application.launch(CenterController.class);

			Log.i("UI launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - UI launch failed, System exits", e);
			System.exit(1);
		}
	}

	private static final void launchAccessor() {
		InfoModifyAccessorImpl.launch();
		MemberLoginAccessorImpl.launch();
		MemberOrderOperationAccessorImpl.launch();
		RegisterAccessorImpl.launch();
		SearchOrderInfoAccessorImpl.launch();
		HotelSearchAccessorImpl.launch();
		CreditChangeOrderAccessorImpl.launch();
		OrderMakerAccessorImpl.launch();
		// HotelNameAccessorImpl.launch();

	}

	private static final void launchManager() {
		CreditChangeManagerImpl.launch();
		MemberInfoManagerImpl.launch();
		MyOrderManagerImpl.launch();
		SearchHotelManagerImpl.launch();
		HotelCommentManagerImpl.launch();
		HotelPromotionManagerImpl.launch();
		HistoryOrderManagerImpl.launch();
	}

	private UILauncher() {

	}

}
