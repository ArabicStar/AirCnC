package interactor.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.manage.ManageMarketInteractor;
import service.manage.ManageMarketService;

public class ManageMarketCourier implements ManageMarketInteractor{
	/* singleton */
	private static ManageMarketInteractor instance;

	public static final ManageMarketInteractor launch(ManageMarketService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageMarketCourier(handler);
	}

	public static final ManageMarketInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private ManageMarketService handler;

	private ManageMarketCourier(ManageMarketService handler) {
		this.handler = handler;
	}

	@Override
	public void AddMarketInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ModifyMarketInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMarketInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMarketInfo() {
		// TODO Auto-generated method stub
		
	}
}
