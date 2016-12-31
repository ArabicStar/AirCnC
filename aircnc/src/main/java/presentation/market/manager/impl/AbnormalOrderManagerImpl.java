package presentation.market.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.market.manager.AbnormalOrderManager;
import presentation.market.model.OrderModel;
import vo.order.OrderVo;

public class AbnormalOrderManagerImpl implements AbnormalOrderManager{

	private static AbnormalOrderManagerImpl instance;
	
	private List<OrderVo> orders;
	
	private ObservableList<OrderModel> orderData;

	
	public static final AbnormalOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new AbnormalOrderManagerImpl();
	}
	
	public static final AbnormalOrderManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public void setAbnormalOrders(List<OrderVo> orders) {
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
