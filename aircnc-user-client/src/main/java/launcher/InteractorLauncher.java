package launcher;

import interactor.impl.member.MemberAccountCourier;
import interactor.impl.member.MemberInfoCourier;
import service.member.MemberServiceProxy;

public final class InteractorLauncher {
	public static void launch() {
		launchMemberInteractor();
	}

	private static void launchMemberInteractor() {
		final MemberServiceProxy proxy = MemberServiceProxy.getInstance();

		MemberAccountCourier.launch(proxy);
		MemberInfoCourier.launch(proxy, proxy);
	}

	private InteractorLauncher() {
	}
}
