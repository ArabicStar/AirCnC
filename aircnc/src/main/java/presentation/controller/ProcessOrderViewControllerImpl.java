package presentation.controller;

import java.util.List;

import presentation.view.ProcessOrderView;
import presentation.view.ProcessOrderViewControllerService;
import service.OrderService;
import service.impl.OrderServiceImpl;
import vo.OrderVo;

public class ProcessOrderViewControllerImpl implements ProcessOrderViewControllerService{
	
	private int hotelId;
	
	private OrderService orderService;
	
//	private UserService userService;
	
	private ProcessOrderView view;
	
	public ProcessOrderViewControllerImpl(int hotelId){
		this.hotelId = hotelId;
		orderService = new OrderServiceImpl(hotelId);
//		userService = new UserServiceImpl();
	}
	
	public void setView(ProcessOrderView view){
		this.view = view;
	}
	
	public int getHotelId(){
		return hotelId;
	}
	

	public List<OrderVo> getAllOrders(int hotelId){
		return orderService.getAllOrders(hotelId);
	}
	

	public List<OrderVo> getUnfinishedOrders(int hotelId){
		return orderService.getUnfinishedOrders(hotelId);
	}
	

	public List<OrderVo> getFinishedOrders(int hotelId){
		return orderService.getFinishedOrders(hotelId);
	}
	

	public List<OrderVo> getAbnormalOrders(int hotelId){
		return orderService.getAbnormalOrders(hotelId);
	}
	

	public boolean processUnfinishedOrder(int orderId){
		if(orderService.finishOrder(orderId)){
			int userId = orderService.getOrderUser(orderId);
			int price = orderService.getOrderPrice(orderId);
return false;
			//return userService.addUserCredit(userId, price);
		}
		return false;
		
	}
	

	public boolean processAbnormalOrder(int orderId,String delayTime){
		if(orderService.delayOrder(orderId, delayTime)){
			int userId = orderService.getOrderUser(orderId);
			int price = orderService.getOrderPrice(orderId);
return false;
			//return userService.addUserCredit(userId, price);
		}
		return false;
		
	}
	

	public void updateListModel(String comboboxValue){
		view.updateListModel(comboboxValue);
	}
	

	public void processOrderButtonClicked(){
		view.processOrderButtonClicked();
	}

	public void delayOrderButtonClicked() {
		view.delayOrderButtonClicked();
	}

	
}
