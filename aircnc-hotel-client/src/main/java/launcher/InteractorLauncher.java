package launcher;

import interactor.impl.hotel.HotelAccountCourier;
import interactor.impl.hotel.HotelInfoCourier;
import service.hotel.HotelServiceProxy;

public final class InteractorLauncher {
	public static void launch() {
		launchHotelInteractor();
	}

//	private static void launchMemberInteractor() {
//		final MemberServiceProxy proxy = MemberServiceProxy.getInstance();
//
//		MemberAccountCourier.launch(proxy);
//		MemberInfoCourier.launch(proxy, proxy);
//
//	}

	private static void launchHotelInteractor() {
		final HotelServiceProxy proxy = HotelServiceProxy.getInstance();

		HotelAccountCourier.launch(proxy);
		HotelInfoCourier.launch(proxy, proxy);
	}

	private static void launchManageInteractor() {

	}

	private static void launchMarketInteractor() {

	}

	private InteractorLauncher() {
	}
}
