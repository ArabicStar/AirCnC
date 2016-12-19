package launcher;

import javafx.application.Application;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.CenterController;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.accessor.impl.RegisterAccessorImpl;
import presentation.member.accessor.impl.SearchHotelInfoAccessorImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import utils.logger.Log;

public class UILauncher {
	public static final void launch() {
		try {
			launchMemberUI();

			Log.i("UI launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - UI launch failed, System exits", e);
			System.exit(1);
		}
		Application.launch(CenterController.class);
	}

	private UILauncher() {
	}
	
	private static final void launchMemberUI() {
		InfoModifyAccessorImpl.launch();
		MemberAppealAccessorImpl.launch();
		MemberCommentAccessorImpl.launch();
		MemberLoginAccessorImpl.launch();
		MemberOrderOperationAccessorImpl.launch();
		RegisterAccessorImpl.launch();
		SearchHotelInfoAccessorImpl.launch();
		SearchOrderInfoAccessorImpl.launch();
		SupremeSearchAccessorImpl.launch();
		
		CreditChangeManagerImpl.launch();
		MemberInfoManagerImpl.launch();
		MyOrderManagerImpl.launch();
		SearchHotelManagerImpl.launch();
		
		
		
	}
}
