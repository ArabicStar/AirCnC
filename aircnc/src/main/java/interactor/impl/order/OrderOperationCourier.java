package interactor.impl.order;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unknownEx;

import interactor.order.UserOrderOperationInteractor;
import interactor.utils.Title;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import service.member.MemberAccountService;
import service.order.OrderOperationService;
import service.promotion.PromotionApplicationService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;

public class OrderOperationCourier implements UserOrderOperationInteractor {
	
	private static UserOrderOperationInteractor instance;

	public static UserOrderOperationInteractor launch(OrderOperationService handler
			, PromotionApplicationService promotion, MemberAccountService acc) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderOperationCourier(handler, promotion, acc);
	}

	public static UserOrderOperationInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private OrderOperationService handler;
	private PromotionApplicationService promotion;
	private MemberAccountService acc;

	public OrderOperationCourier(OrderOperationService handler
			, PromotionApplicationService promotion, MemberAccountService acc){
		this.handler = handler;
		this.promotion = promotion;
		this.acc = acc;
	}
	
	@Override
	public boolean tryMakeOrder() {
		String title = getTitle();
		if (!acc.isLoggedin()) {
			alertFail(title, "尚未登录！");
			return false;
		}

		final MemberInfo member = acc.getCurrentAccount();
		if (member.getCredit() < 0) {
			alertFail(title, "预订失败，您的信用值不足！");
			return false;
		}

		OrderInfo order = execute(title,
				() -> promotion.applyPromotion(getNewOrderFromUI().setMember(member).getOrderInfo()));

		return order != null;
	}

	@Override
	@Title("生成订单")
	public boolean makeOrder() {
		String title = getTitle();
		if (!acc.isLoggedin()) {
			alertFail(title, "尚未登录！");
			return false;
		}

		OrderInfo order = execute(title, () -> {
			OrderInfo info = handler.makeOrder(getOrderFromUI());

			if (info == null)
				alertFail(title, "预订失败，您的信用值不足！");

			if (!info.isValid())
				throw unknownEx();

			return info;
		});

		if (order != null)
			alertSuccess(title, "预定成功！您可以到 我的订单 中查看订单信息");

		acc.refreshCurrentAccount();
		return order != null;
	}

	@Override
	public boolean cancelOrder() {
		String title = getTitle();
		MemberInfo mem = execute(title, () -> {
			MemberInfo info = handler.cancelOrder(getOrderFromUI());

			if (info == null || !info.isValid())
				throw unknownEx();

			return info;
		});

		if (mem != null)
			alertSuccess(title, String.format("订单取消成功！您现在的信用值是%d", mem.getCredit()));

		acc.refreshCurrentAccount();
		return mem != null;
	}

	private static final OrderInfo getOrderFromUI() {
		return MemberOrderOperationAccessorImpl.getInstance().getOrder();
	}

	private static final OrderInfoBuilder getNewOrderFromUI() {
		// return MemberOrderOperationAccessorImpl.getInstance().getOrder();
		return null;
	}
}
