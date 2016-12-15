package interactor.impl.member;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unknownEx;

import interactor.member.MemberAccountInteractor;
import interactor.utils.Title;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.accessor.impl.RegisterAccessorImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import service.member.MemberAccountService;
import utils.info.member.MemberInfo;

public final class MemberAccountCourier implements MemberAccountInteractor {
	/* singleton */
	private static MemberAccountInteractor instance;

	public static final MemberAccountInteractor launch(MemberAccountService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberAccountCourier(handler);
	}

	public static final MemberAccountInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* singleton */

	private MemberAccountService handler;

	private MemberAccountCourier(MemberAccountService handler) {
		this.handler = handler;
	}

	@Override
	@Title("Register")
	public void register() {
		String title = getTitle();
		MemberInfo info = execute(title, () -> {
			MemberInfo tmp = handler.register(RegisterAccessorImpl.getInstance().getNewAccountInfo()
					, RegisterAccessorImpl.getInstance().getPasswordHash());
			if (tmp == null)
				throw unknownEx();
			return tmp;
		});

	 MemberInfoManagerImpl.getInstance().setUser(info);
	}

	@Override
	@Title("Login")
	public void login() {
		String title = getTitle();
		MemberInfo info = execute(title, () -> {
			MemberInfo tmp = handler.login(MemberLoginAccessorImpl.getInstance().getId()
					, MemberLoginAccessorImpl.getInstance().getPasswordHash());

			if (tmp == null)
				alertFail(title, "Wrong or not exist id");

			if (!tmp.isValid())
				alertFail(title, "Wrong password");

			return tmp;
		});

		MemberInfoManagerImpl.getInstance().setUser(info);
	}

	@Override
	@Title("Logout")
	public void logout() {
		execute(getTitle(), () -> {
			return handler.logout();
		});
	}

	@Override
	@Title("Refresh Account")
	public void refreshCurrentAccount() {
		execute(getTitle(), () -> {
			return handler.refreshCurrentAccount();
		});

	}

}
