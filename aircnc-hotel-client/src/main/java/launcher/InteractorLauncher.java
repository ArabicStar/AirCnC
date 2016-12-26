package launcher;

import interactor.impl.hotel.HotelAccountCourier;
import interactor.impl.hotel.HotelInfoCourier;
import interactor.impl.hotel.HotelOrderCourier;
import interactor.impl.hotel.HotelPromotionCourier;
import service.hotel.HotelServiceProxy;
import service.promotion.PromotionServiceProxy;

public final class InteractorLauncher {
	public static void launch() {
		launchHotelInteractor();
	}

	private static void launchHotelInteractor() {
		final HotelServiceProxy hotelProxy = HotelServiceProxy.getInstance();
		final PromotionServiceProxy promotionProxy = PromotionServiceProxy.getInstance();

		HotelAccountCourier.launch(hotelProxy);
		HotelInfoCourier.launch(hotelProxy, hotelProxy);
		HotelPromotionCourier.launch(promotionProxy, hotelProxy);
		HotelOrderCourier.launch(hotelProxy, hotelProxy);
	}

	private InteractorLauncher() {
	}
}
