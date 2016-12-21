package launcher;

import interactor.impl.manage.ManageHotelCourier;
import interactor.impl.manage.ManageMarketCourier;
import interactor.impl.manage.ManageMemberCourier;
import service.manage.ManageServiceProxy;

public class InteractorLauncher {
	public static void launch() {
		launchManageInteractor();
	}

	private static void launchManageInteractor() {
		final ManageServiceProxy proxy = ManageServiceProxy.getInstance();

		ManageHotelCourier.launch(proxy);
		ManageMarketCourier.launch(proxy);
		ManageMemberCourier.launch(proxy);

	}
	
	private InteractorLauncher(){
		
	}
}
