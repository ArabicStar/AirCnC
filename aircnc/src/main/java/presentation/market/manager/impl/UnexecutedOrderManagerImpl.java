package presentation.market.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.market.manager.UnexecutedOrderManager;
import presentation.market.model.OrderModel;
import vo.order.OrderVo;

public class UnexecutedOrderManagerImpl implements UnexecutedOrderManager{
private static UnexecutedOrderManagerImpl instance;
	
	private List<OrderVo> orders;
	
	private ObservableList<OrderModel> orderData;

	
	public static final UnexecutedOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new UnexecutedOrderManagerImpl();
	}
	
	public static final UnexecutedOrderManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public void setUnexecutedOrders(List<OrderVo> orders) {
		this.orders = orders;
		
	}

	@Override
	public ObservableList<OrderModel> getOrderList() {
		orderData = FXCollections.observableArrayList();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			orderData.add(new OrderModel(it.next()));
		return orderData;
	}
}
