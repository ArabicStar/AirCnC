package interactor.impl.order;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import interactor.order.OrderInfoInteractor;
import interactor.utils.Title;
import po.order.OrderPo;
import presentation.member.accessor.impl.CreditChangeOrderAccessorImpl;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import service.order.OrderDetailService;
import service.order.OrderListingService;
import service.order.OrderLogicService;
import vo.order.OrderVo;

public class OrderInfoCourier implements OrderInfoInteractor{
	private static OrderInfoInteractor instance;
	
	private OrderDetailService detail;
	private OrderListingService listing;
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
		OrderVo info = interactor.utils.Dipatcher.execute(title, () -> {
			String id = getCurrentId();
			if(detail != null) {
				return detail.getOrderInfoById(id).orderPo2Vo();
			}
			alertFail(title, "Not logged in yet");
			return null;
		});

		CreditChangeManagerImpl.getInstance().setCauseOrder(info);
	}
	
	private String getCurrentId() {
		return CreditChangeOrderAccessorImpl.getInstance().getCauseId();
	}

	@Override
	public void delay() {
		// TODO Auto-generated method stub
		
	}

	@Title("Get OrderInfo by id")
	@Override
	public void getOrderInfoById(String orderId) {
		String title = getTitle();	
		OrderVo info = interactor.utils.Dipatcher.execute(title, () -> {
			if(detail != null) {
				return detail.getOrderInfoById(orderId).orderPo2Vo();
			}
			alertFail(title, "Not logged in yet");
			return null;
		});
		CreditChangeManagerImpl.getInstance().setCauseOrder(info);
	}

	@Override
	public void execute() {
		
	}

	/**
	 * 从id拿到订单，把订单的
	 */
	@Override
	public void makeComment() {
		OrderPo orderPo = detail.getOrderInfoById(MemberCommentAccessorImpl.getInstance().getId());
		orderPo.getComments();
		MemberCommentAccessorImpl.getInstance().getComment();
		
	}

	@Override
	public void repeal() {
		// TODO Auto-generated method stub
//		MemberOrderOperationAccessorImpl.getInstance(). 无获取订单状态的方法
//		MemberCommentAccessorImpl.getInstance
//		MemberAppealAccessorImpl.getInstance()
		// 调用credit逻辑
		
	}

	@Override
	public void makeAppeal() {
		// TODO Auto-generated method stub
		MemberOrderOperationAccessorImpl.getInstance().getAppeal();
		MemberAppealAccessorImpl.getInstance().setAppeal("");
		
	}

	@Override
	public void getOrderInfoByHotel() {
		// TODO Auto-generated method stub
		
	}
	
}
