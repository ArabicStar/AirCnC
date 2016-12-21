package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.hotel.manager.HotelOrderManager;
import presentation.hotel.model.CheckOrderModel;
import presentation.hotel.model.OrderModel;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class HotelOrderManagerImpl implements HotelOrderManager{
	private static HotelOrderManager instance;
	
	private List<OrderVo> orders;
	
	private ObservableList<CheckOrderModel> checkOrderData;
	
	private ObservableList<OrderModel> orderData;
	
	public static final HotelOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelOrderManagerImpl();
	}
	
	public static final HotelOrderManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	@Override
	public boolean setOrderList(List<OrderVo> list) {
		if(list!=null){
			this.orders = list;
			return true;
		}

		return false;
	}

	@Override
	public ObservableList<CheckOrderModel> getCheckOrderList() {
		checkOrderData = FXCollections.observableArrayList();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			checkOrderData.add(new CheckOrderModel(it.next()));
		return checkOrderData;
	}

	@Override
	public ObservableList<OrderModel> getOrderList() {
		orderData = FXCollections.observableArrayList();
		test();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			orderData.add(new OrderModel(it.next()));
		return orderData;
	}
	
	private void test(){
		orders = new ArrayList<OrderVo>();
		OrderVoBuilder builder = new OrderVoBuilder().setUserName("小手表").setUserId(13245364).
				setHotelId(3).setEntryTime(LocalDateTime.now()).setStayDays(5).
				setRoomNumber(3).setDiscountPrice(20).setOriginalPrice(900).setOrderId("24383759");
		orders.add(builder.getOrderInfo());
		
		orders.add(builder.getOrderInfo());
		
		orders.add(builder.getOrderInfo());
//    	orderData.add(new OrderModel("小手表","233","101","2016-10-09","5晚/1间","290元"));
//		orderData.add(new OrderModel("小手表","233","102","2016-10-12","2晚/1间","1000元"));
//		orderData.add(new OrderModel("小手表","233","103","2016-10-15","10晚/1间","400元"));
//		orderData.add(new OrderModel("小手表","233","104","2016-10-30","1晚/10间","2950元"));
	}

}
