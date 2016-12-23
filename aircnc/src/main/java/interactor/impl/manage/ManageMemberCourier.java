package interactor.impl.manage;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.manage.ManageMemberInteractor;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import service.manage.ManageMemberService;
import utils.info.member.MemberInfo;
import vo.member.MemberVoBuilder;

public class ManageMemberCourier implements ManageMemberInteractor{
	/* singleton */
	private static ManageMemberInteractor instance;

	public static final ManageMemberInteractor launch(ManageMemberService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageMemberCourier(handler);
	}

	public static final ManageMemberInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private ManageMemberService handler;

	private ManageMemberCourier(ManageMemberService handler) {
		this.handler = handler;
	}
	
	@Override
	public void ModifyMemberInfo() {
		String title = getTitle();
		MemberInfo modified = MemberManageInfoAccessorImpl.getInstance().getModifiedMemberVo();

		boolean res = execute(title, () -> {
			String id = MemberManageInfoAccessorImpl.getInstance().getMemberId();
			if (id != null)
				return handler.ModifyMemberInfo(modified);

			alertFail(title, "Not input the modified info yet");
			return null;
		});

		MemberManageInfoImpl.getInstance().setUser(res ? new MemberVoBuilder(modified).getMemberInfo() : null);
	}

	@Override
	public void getMemberInfo() {
		String title = getTitle();

		MemberInfo info = execute(title, () -> {
			String id = MemberManageInfoAccessorImpl.getInstance().getMemberId();
			if (id != null)
				return handler.getMemberInfo(id);

			alertFail(title, "Not input Member id yet");
			return null;
		});

		MemberManageInfoImpl.getInstance().setUser(new MemberVoBuilder(info).getMemberInfo());
	}

}
