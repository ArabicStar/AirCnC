package launcher;

import interactor.impl.market.MarketAccountCourier;
import interactor.impl.market.MarketInfoCourier;
import interactor.impl.market.MarketPromotionCourier;
import interactor.impl.market.MarketServiceCourier;
import service.market.MarketServiceProxy;
import service.order.OrderServiceProxy;
import service.promotion.PromotionServiceProxy;

public class InteractorLauncher {
	public static void launch() {
		launchMarketInteractor();
	}

	private static void launchMarketInteractor() {
		final MarketServiceProxy marketProxy = MarketServiceProxy.getInstance();
		final PromotionServiceProxy promotionProxy = PromotionServiceProxy.getInstance();
		final OrderServiceProxy orderProxy = OrderServiceProxy.getInstance();
		
		MarketAccountCourier.launch(marketProxy);
		MarketInfoCourier.launch(marketProxy, marketProxy);
		MarketPromotionCourier.launch(promotionProxy, marketProxy);
		MarketServiceCourier.launch(marketProxy, orderProxy);
	}

	private InteractorLauncher() {
	}
}
