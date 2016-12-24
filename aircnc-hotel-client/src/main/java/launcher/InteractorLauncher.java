package launcher;

import interactor.impl.hotel.HotelAccountCourier;
import interactor.impl.hotel.HotelInfoCourier;
import interactor.impl.hotel.HotelPromotionCourier;
import service.hotel.HotelServiceProxy;
import service.promotion.PromotionServiceProxy;

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
		final HotelServiceProxy hotelProxy = HotelServiceProxy.getInstance();
		final PromotionServiceProxy promotionProxy = PromotionServiceProxy.getInstance();

		HotelAccountCourier.launch(hotelProxy);
		HotelInfoCourier.launch(hotelProxy, hotelProxy);
		HotelPromotionCourier.launch(promotionProxy, hotelProxy);
	}

	private static void launchManageInteractor() {

	}

	private static void launchMarketInteractor() {

	}

	private InteractorLauncher() {
	}
}
