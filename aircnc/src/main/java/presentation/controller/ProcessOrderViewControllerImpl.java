package presentation.controller;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProcessOrderViewControllerImpl implements ProcessOrderViewControllerService{

	@FXML
	private TextField Username;

	@FXML
	private TextField Password;
=======
import java.util.List;

import presentation.view.ProcessOrderView;
import presentation.view.ProcessOrderViewControllerService;
import service.impl.order.OrderDetailServiceImpl;
import service.impl.order.OrderListingServiceImpl;
import service.impl.order.OrderLogicServiceImpl;
import service.impl.order.OrderServiceImpl;
import service.order.OrderDetailService;
import service.order.OrderListingService;
import service.order.OrderLogicService;
import service.order.OrderService;
import vo.order.OrderVo;

public class ProcessOrderViewControllerImpl implements ProcessOrderViewControllerService {

	private int hotelId;

//	private OrderService orderService;
	private OrderDetailService orderDetailService;
	private OrderListingService orderListingService;
	private OrderLogicService orderLogicService;

	// private UserService userService;

	private ProcessOrderView view;

	public ProcessOrderViewControllerImpl(int hotelId) {
		this.hotelId = hotelId;
//		orderService = new OrderServiceImpl(hotelId);
		orderDetailService = new OrderDetailServiceImpl(hotelId);
		orderListingService = new OrderListingServiceImpl(hotelId);
		orderLogicService = new OrderLogicServiceImpl(hotelId);
		// userService = new UserServiceImpl();
	}

	public void setView(ProcessOrderView view) {
		this.view = view;
	}

	public int getHotelId() {
		return hotelId;
	}

	public List<OrderVo> getAllOrders(int hotelId) {
		return orderListingService.getAllOrders(hotelId);
	}

	public List<OrderVo> getUnfinishedOrders(int hotelId) {
		return orderListingService.getUnfinishedOrders(hotelId);
	}

	public List<OrderVo> getFinishedOrders(int hotelId) {
		return orderListingService.getFinishedOrders(hotelId);
	}

	public List<OrderVo> getAbnormalOrders(int hotelId) {
		return orderListingService.getAbnormalOrders(hotelId);
	}

	public boolean processUnfinishedOrder(int orderId) {
		if (orderLogicService.finishOrder(orderId)) {
			int userId = orderDetailService.getOrderUser(orderId);
			int price = orderDetailService.getOrderPrice(orderId);
			return false;
			// return userService.addUserCredit(userId, price);
		}
		return false;

	}

	public boolean processAbnormalOrder(int orderId, String delayTime) {
		if (orderLogicService.delayOrder(orderId, delayTime)) {
			int userId = orderDetailService.getOrderUser(orderId);
			int price = orderDetailService.getOrderPrice(orderId);
			return false;
			// return userService.addUserCredit(userId, price);
		}
		return false;

	}

	public void updateListModel(String comboboxValue) {
		view.updateListModel(comboboxValue);
	}

	public void processOrderButtonClicked() {
		view.processOrderButtonClicked();
	}
>>>>>>> origin/master


}
