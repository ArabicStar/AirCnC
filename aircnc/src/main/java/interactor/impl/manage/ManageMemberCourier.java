package interactor.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.manage.ManageMemberInteractor;
import service.manage.ManageMemberService;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMemberInfo() {
		// TODO Auto-generated method stub
		
	}

}
