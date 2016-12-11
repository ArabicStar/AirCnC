package launcher;

import data.dao.member.MemberDaoProxy;
import data.dao.query.QueryDaoProxy;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberCreditManager;
import service.member.MemberAccountService;
import service.member.MemberCreditService;
import service.member.MemberServiceProxy;
import utils.logger.Log;
import utils.proxy.AuthenticatePolicy.Client;

public class ServiceLauncher {
	public static final void launch(Client clientId) {
		try {
			launchMemberService(clientId);

			Log.i("Services launch succeed");
		} catch (Exception e) {
			Log.f("FATAL ERROR - Services launch failed, System exits", e);
			System.exit(1);
		}
	}

	private ServiceLauncher() {
	}

	private static final void launchMemberService(Client clientId) {
		MemberDaoProxy memberDaoProxy = MemberDaoProxy.getInstance();

		MemberServiceProxy proxy = MemberServiceProxy.launch(clientId);

		MemberAccountService acc = MemberAccountManager.launch(memberDaoProxy);
		MemberCreditService cre = MemberCreditManager.launch(memberDaoProxy, memberDaoProxy);

		proxy.loadAccountService(acc);
		proxy.loadCreditService(cre);
	}
}
