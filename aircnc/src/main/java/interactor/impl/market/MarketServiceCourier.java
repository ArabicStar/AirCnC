package interactor.impl.market;

import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;

import interactor.market.MarketServiceInteractor;
import interactor.utils.Title;
import presentation.market.accessor.MarketChargeAccessor;
import presentation.market.accessor.impl.AbnormalOrderAccessorImpl;
import presentation.market.accessor.impl.MarketChargeAccessorImpl;
import presentation.market.manager.impl.AbnormalOrderManagerImpl;
import service.market.MarketService;
import service.order.OrderOperationService;
import utils.info.member.MemberInfo;
import vo.order.OrderVo;

public class MarketServiceCourier implements MarketServiceInteractor{
	private static MarketServiceInteractor instance;

	public static MarketServiceInteractor launch(MarketService handler, OrderOperationService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketServiceCourier(handler, helper);
	}

	public static MarketServiceInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private MarketService handler;
	private OrderOperationService helper;

	private MarketServiceCourier(MarketService handler,OrderOperationService helper) {
		this.handler = handler;
		this.helper = helper;
	}

	@Override
	@Title("获取申诉订单")
	public void getAbnormalOrder() {
		String title = getTitle();
		List<OrderVo> orders = execute(title, () -> {
			return handler.getAbnormalOrder();
		});
		AbnormalOrderManagerImpl.getInstance().setAbnormalOrders(orders);
	}
	
	@Override
	@Title("处理申诉订单")
	public void approveOrder(){
		String title = getTitle();

		execute(title, () -> {
			MemberInfo member = helper.approveAppeal(AbnormalOrderAccessorImpl.getInstance().getOrderVo());
			alertSuccess(title, "申诉成功！\n目前"+member.getId()+"用户信用值为"+member.getCredit());
			return 0;
		});
	}

	@Override
	@Title("信用充值")
	public void creditCharge() {
		
		String title = getTitle();

		execute(title, () -> {
			MarketChargeAccessor accessor = MarketChargeAccessorImpl.getInstance();
			if(handler.creditCharge(accessor.getTopupMoney(),accessor.getMemberId()))
				alertSuccess(title, "充值成功");
			return 0;
		});		
	}
}
