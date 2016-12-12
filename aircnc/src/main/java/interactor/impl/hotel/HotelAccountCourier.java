package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.hotel.HotelAccountInteractor;
import interactor.utils.Dipatcher;
import interactor.utils.Title;
import presentation.hotel.accessor.HotelLoginAccessor;
import service.hotel.HotelAccountService;
import service.impl.hotel.HotelInfoManager;
import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;

public class HotelAccountCourier implements HotelAccountInteractor{
	/* singleton */
	private static HotelAccountInteractor instance;
	
	public static final HotelAccountInteractor launch(HotelAccountService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelAccountCourier(handler);
	}
	
	public static final HotelAccountInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */

	private HotelAccountService handler;

	private HotelAccountCourier(HotelAccountService handler) {
		this.handler = handler;
	}
	
	@Override
	@Title("Login")
	public void login(HotelLoginAccessor acs, HotelInfoManager man) {
		String title = getTitle();
		HotelInfo info = execute(title, () -> {
			HotelInfo tmp = handler.login(acs.getName(), acs.getPasswordHash());

			if (tmp == null)
				alertFail(title, "Wrong or not exist name");

			if (!tmp.isValid())
				alertFail(title, "Wrong password");

			return tmp;
		});

		man.setHotel(info);
		
	}

	@Override
	@Title("Log Out")
	public void logout() {
		// TODO Auto-generated method stub
		execute(getTitle(), () -> {
			return handler.logout();
		});
	}

	@Override
	@Title("Refresh Account")
	public void refreshCurrentAccount(HotelInfoManager man) {
		execute(getTitle(), () -> {
			return handler.refreshCurrentAccount();
		});
	}

}
