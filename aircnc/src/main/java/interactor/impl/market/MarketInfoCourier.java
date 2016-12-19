package interactor.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.market.MarketInfoInteractor;
import service.market.MarketAccountService;
import service.market.MarketInfoService;

public class MarketInfoCourier implements MarketInfoInteractor{
	
	private static MarketInfoInteractor instance;

	public static MarketInfoInteractor launch(MarketInfoService handler, MarketAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketInfoCourier(handler, helper);
	}

	public static MarketInfoInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private MarketInfoService handler;
	private MarketAccountService helper;

	private MarketInfoCourier(MarketInfoService handler, MarketAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}
	
	@Override
	public void getMarketInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMarketPromotion() {
		// TODO Auto-generated method stub
		
	}

}
