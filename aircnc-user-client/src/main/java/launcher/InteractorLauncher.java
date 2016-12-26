package launcher;

import interactor.impl.member.HotelSearchCourier;
import interactor.impl.member.MemberAccountCourier;
import interactor.impl.member.MemberInfoCourier;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import service.member.MemberServiceProxy;
import service.query.HotelQueryService;
import service.query.QueryServiceProxy;

public final class InteractorLauncher {
	public static void launch() {
		launchMemberInteractor();
		launchHotelInteractor();
	}

	private static void launchMemberInteractor() {
		final MemberAccountService acc = MemberServiceProxy.getInstance();
		final MemberInfoService info = MemberServiceProxy.getInstance();

		MemberAccountCourier.launch(acc);
		MemberInfoCourier.launch(info, acc);

	}

	private static void launchHotelInteractor() {
		final HotelQueryService hotelQuery = QueryServiceProxy.getInstance();

		HotelSearchCourier.launch(hotelQuery);
	}

	private static void launchManageInteractor() {

	}

	private static void launchMarketInteractor() {

	}

	private InteractorLauncher() {
	}
}
