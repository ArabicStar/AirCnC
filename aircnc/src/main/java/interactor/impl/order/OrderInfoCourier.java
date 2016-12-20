package interactor.impl.order;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.order.OrderInfoInteractor;
import interactor.utils.Title;
import service.order.OrderDetailService;
import service.order.OrderListingService;
import service.order.OrderLogicService;
import utils.info.order.OrderInfo;

public class OrderInfoCourier implements OrderInfoInteractor{
	private static OrderInfoInteractor instance;
	
	private OrderDetailService detail;
	@SuppressWarnings("unused")
	private OrderListingService listing;
	@SuppressWarnings("unused")
	private OrderLogicService logic;
	
	private OrderInfoCourier(OrderDetailService detail, OrderListingService listing,
			OrderLogicService logic) {
		this.detail = detail;
		this.listing = listing;
		this.logic = logic;
	}
	
	// singleton
	public static OrderInfoInteractor launch(OrderDetailService detail,
			OrderListingService listing, OrderLogicService logic) {
		if(instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new OrderInfoCourier(detail, listing, logic);
	}
	
	public static OrderInfoInteractor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}

	@Override
	@Title("Get Order Info")
	public void getOrderInfo() {
		String title = getTitle();	
		@SuppressWarnings("unused")
		OrderInfo info = execute(title, () -> {
			String id = getCurrentId();
			if (id != null) {
				return detail.getOrderInfoById(id);
			}
			alertFail(title, "Not logged in yet");
			return null;
		});
		// TODO
//		OrderInfoManagerImpl.getInstance().setOrder(info);
	}
	
	// TODO 未实现的方法
	private String getCurrentId() {
		return null;
	}
	
}
