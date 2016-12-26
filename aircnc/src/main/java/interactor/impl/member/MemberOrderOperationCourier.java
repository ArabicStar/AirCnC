package interactor.impl.member;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unknownEx;

import interactor.member.MemberOrderOperationInteractor;
import interactor.utils.Title;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.manager.impl.MakeOrderManagerImpl;
import service.member.MemberAccountService;
import service.order.OrderOperationService;
import service.promotion.PromotionApplicationService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class MemberOrderOperationCourier implements MemberOrderOperationInteractor {

	private static MemberOrderOperationInteractor instance;

	public static MemberOrderOperationInteractor launch(OrderOperationService handler,
			PromotionApplicationService promotion, MemberAccountService acc) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberOrderOperationCourier(handler, promotion, acc);
	}

	public static MemberOrderOperationInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private OrderOperationService handler;
	private PromotionApplicationService promotion;
	private MemberAccountService acc;

	public MemberOrderOperationCourier(OrderOperationService handler, PromotionApplicationService promotion,
			MemberAccountService acc) {
		this.handler = handler;
		this.promotion = promotion;
		this.acc = acc;
	}

	@Override
	@Title("确认订单")
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
				() -> promotion.applyPromotion(getOrderFromMakerUI().setMember(member).getOrderInfo()));
		MakeOrderManagerImpl.getInstance().setOrderVo(order);
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

		MemberInfo member = acc.getCurrentAccount();
		OrderInfo order = execute(title, () -> {
			OrderInfo info = handler.makeOrder(getOrderFromMakerUI().setMember(member).getOrderInfo());

			if (info == null) {
				alertFail(title, "预订失败，您的信用值不足！");
				return null;
			}

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
			MemberInfo info = handler.cancelOrder(getOrderFromOperationUI());

			if (info == null || !info.isValid())
				throw unknownEx();

			return info;
		});

		if (mem != null)
			alertSuccess(title, String.format("订单取消成功！您现在的信用值是%d", mem.getCredit()));

		acc.refreshCurrentAccount();
		return mem != null;
	}

	@Override
	public boolean appealOrder() {
		String title = getTitle();

		OrderInfo order = execute(title, () -> {
			OrderInfo info = handler.appealOrder(getOrderFromOperationUI());

			if (info == null || !info.isValid())
				throw unknownEx();

			return info;
		});

		if (order != null)
			alertSuccess(title, "已提交申诉！");

		return order != null;
	}

	@Override
	public boolean commentOrder() {
		String title = getTitle();

		OrderInfo order = execute(title, () -> {
			OrderInfo info = handler.commentOrder(getOrderFromOperationUI());

			if (info == null || !info.isValid())
				throw unknownEx();

			return info;
		});

		if (order != null)
			alertSuccess(title, "评价成功！");

		return order != null;
	}

	private final OrderVo getOrderFromOperationUI() {
		return attachMemberInfo(MemberOrderOperationAccessorImpl.getInstance().getOrder());
	}

	private final OrderVoBuilder getOrderFromMakerUI() {
		return OrderMakerAccessorImpl.getIntance().getMadeOrder();
	}

	private OrderVo attachMemberInfo(OrderInfo info) {
		return new OrderVoBuilder(info).setMember(acc.getCurrentAccount()).getOrderInfo();
	}
}
