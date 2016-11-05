package presentation.controller;

import java.util.List;

import javax.swing.text.View;

import presentation.view.ProcessOrderView;
import presentation.view.ProcessOrderViewControllerService;
import service.OrderService;
import service.UserService;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;
import vo.OrderVo;

public class ProcessOrderViewControllerImpl implements ProcessOrderViewControllerService{
	
	private int hotelId;
	
	private OrderService orderService;
	
	private UserService userService;
	
	private ProcessOrderView view;
	
	public ProcessOrderViewControllerImpl(int hotelId){
		this.hotelId = hotelId;
		orderService = new OrderServiceImpl(hotelId);
		userService = new UserServiceImpl();
	}
	
	public void setView(ProcessOrderView view){
		this.view = view;
	}
	
	public int getHotelId(){
		return hotelId;
	}
	
	/**
	 * @return	获取酒店所有订单
	 */
	public List<OrderVo> getAllOrders(int hotelId){
		return orderService.getAllOrders(hotelId);
	}
	
	/**
	 * @param hotelId
	 * @return	获取酒店未执行订单
	 */
	public List<OrderVo> getUnfinishedOrders(int hotelId){
		return orderService.getUnfinishedOrders(hotelId);
	}
	
	/**
	 * @param hotelId
	 * @return	获取酒店已执行订单
	 */
	public List<OrderVo> getFinishedOrders(int hotelId){
		return orderService.getFinishedOrders(hotelId);
	}
	
	/**
	 * @param hotelId
	 * @return	获取酒店异常订单
	 */
	public List<OrderVo> getAbnormalOrders(int hotelId){
		return orderService.getAbnormalOrders(hotelId);
	}
	
	/**
	 * @param orderId
	 * @return	对未执行订单进行处理
	 */
	public boolean processUnfinishedOrder(int orderId){
		if(orderService.finishOrder(orderId)){
			int userId = orderService.getOrderUser(orderId);
			int price = orderService.getOrderPrice(orderId);
			return userService.addUserCredit(userId, price);
		}
		return false;
		
	}
	
	/**
	 * @param orderId
	 * @param delayTime
	 * @return	为异常订单办理延期入住
	 */
	public boolean processAbnormalOrder(int orderId,String delayTime){
		if(orderService.delayOrder(orderId, delayTime)){
			int userId = orderService.getOrderUser(orderId);
			int price = orderService.getOrderPrice(orderId);
			return userService.addUserCredit(userId, price);
		}
		return false;
		
	}
	
	/**
	 * 更换列表数据源
	 * @param comboboxValue
	 */
	public void updateListModel(String comboboxValue){
		view.updateListModel(comboboxValue);
	}
	
	/**
	 * 处理订单按钮点击事件
	 */
	public void processOrderButtonClicked(){
		view.processOrderButtonClicked();
	}

	/**
	 * 打开订单延期界面
	 */
	public void delayOrderButtonClicked() {
		view.delayOrderButtonClicked();
	}

	
}
