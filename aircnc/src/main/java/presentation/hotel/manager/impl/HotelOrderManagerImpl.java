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

	}

}
