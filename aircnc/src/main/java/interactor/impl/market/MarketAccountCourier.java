package interactor.impl.market;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.market.MarketAccountInfoInteractor;
import interactor.utils.Title;
import presentation.market.accessor.impl.MarketLoginAccessorImpl;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import service.market.MarketAccountService;
import utils.info.market.MarketInfo;

public class MarketAccountCourier implements MarketAccountInfoInteractor{
	/* singleton */
	private static MarketAccountInfoInteractor instance;

	public static final MarketAccountInfoInteractor launch(MarketAccountService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketAccountCourier(handler);
	}

	public static final MarketAccountInfoInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */

	private MarketAccountService handler;

	private MarketAccountCourier(MarketAccountService handler) {
		this.handler = handler;
	}
	
	@Override
	@Title("Login")
	public void login() {
		String title = getTitle();
		MarketInfo info = execute(title, () -> {
			MarketInfo tmp = handler.login(MarketLoginAccessorImpl.getInstance().getId()
					, MarketLoginAccessorImpl.getInstance().getPasswordHash());

			if (tmp == null)
				alertFail(title, "Wrong or not exist id");

			if (!tmp.isValid())
				alertFail(title, "Wrong password");

			return tmp;
		});

//		MarketInfoManagerImpl.getInstance().setUser(info);
	}

	@Override
	@Title("Logout")
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Title("Refresh Account")
	public void refreshCurrentAccount() {
		// TODO Auto-generated method stub
		
	}

}
