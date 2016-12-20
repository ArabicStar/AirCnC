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
		// TODO 这一段本来没有报错，但是突然就报错了
//		OrderInfo info = execute(title, () -> {
//			String id = getCurrentId();
//			if(id != null) {
//				return detail.getOrderById(id);
//			}
//			alertFail(title, "Not logged in yet");
//			return null;
//		});
		String id = getCurrentId();
		OrderInfo info;
		if(id != null) {
			info = detail.getOrderInfoById(id);
			info.getComments();
		} else {
			alertFail(title, "Not logged in yet");
		}
		// TODO
//		OrderInfoManagerImpl.getInstance().setOrder(info);
	}
	
	// TODO 未实现的方法
	private String getCurrentId() {
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getOrderInfoById(String orderId) {
		// TODO Auto-generated method stub
		
	}
	
}
