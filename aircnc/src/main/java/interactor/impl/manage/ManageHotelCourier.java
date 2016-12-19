package interactor.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.manage.ManageHotelInteractor;
import service.manage.ManageHotelService;

public class ManageHotelCourier implements ManageHotelInteractor{
	/* singleton */
	private static ManageHotelInteractor instance;

	public static final ManageHotelInteractor launch(ManageHotelService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageHotelCourier(handler);
	}

	public static final ManageHotelInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private ManageHotelService handler;

	private ManageHotelCourier(ManageHotelService handler) {
		this.handler = handler;
	}
	@Override
	public void AddHotelInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ModifyHotelInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getHotelInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHotelInfo() {
		// TODO Auto-generated method stub
		
	}

}
