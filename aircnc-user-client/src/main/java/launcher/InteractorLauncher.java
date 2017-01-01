package launcher;

import interactor.impl.member.HotelSearchCourier;
import interactor.impl.member.MemberAccountCourier;
import interactor.impl.member.MemberInfoCourier;
import interactor.impl.member.MemberOrderOperationCourier;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import service.member.MemberServiceProxy;
import service.order.OrderOperationService;
import service.order.OrderServiceProxy;
import service.promotion.HotelPromotionInfoService;
import service.promotion.PromotionApplicationService;
import service.promotion.PromotionServiceProxy;
import service.query.CommentQueryService;
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
		final PromotionApplicationService prom = PromotionServiceProxy.getInstance();
		final OrderOperationService ord = OrderServiceProxy.getInstance();

		MemberAccountCourier.launch(acc);
		MemberInfoCourier.launch(info, acc);
		MemberOrderOperationCourier.launch(ord, prom, acc);
	}

	private static void launchHotelInteractor() {

		final HotelQueryService hotelQuery = QueryServiceProxy.getInstance();
		final HotelPromotionInfoService promotionInfo = PromotionServiceProxy.getInstance();
		final CommentQueryService commentQuery = QueryServiceProxy.getInstance();

		HotelSearchCourier.launch(hotelQuery, commentQuery, promotionInfo);
	}

	private InteractorLauncher() {
	}
}
