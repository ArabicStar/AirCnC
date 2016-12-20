package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.hotel.HotelAccountInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.HotelLoginAccessor;
import presentation.hotel.accessor.impl.HotelLoginAccessorImpl;
import presentation.hotel.manager.impl.InfoManagerImpl;
import service.hotel.HotelAccountService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;

public class HotelAccountCourier implements HotelAccountInteractor {
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
	public boolean login() {
		HotelLoginAccessor acs = HotelLoginAccessorImpl.getInstance();
		String title = getTitle();
		HotelInfo info = execute(title, () -> {
			HotelInfo tmp = handler.login(acs.getName(), acs.getPasswordHash());

			if (tmp == null)
				alertFail(title, "Wrong or not exist name");

			if (!tmp.isValid())
				alertFail(title, "Wrong password");

			return tmp;
		});

		InfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		return info != null;

	}

	@Override
	@Title("Log Out")
	public boolean logout() {
		return execute(getTitle(), () -> handler.logout());
	}

	@Override
	@Title("Refresh Account")
	public boolean refreshCurrentAccount() {
		HotelInfo info = execute(getTitle(), () -> handler.refreshCurrentAccount());
		InfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		return info != null;

	}

}
