package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.HistoryOrderManager;
import presentation.member.model.MyOrderModel;
import vo.order.OrderVo;

public class HistoryOrderManagerImpl implements HistoryOrderManager{

private static HistoryOrderManager instance;
	
	private List<OrderVo> orders;
	
	private ObservableList<MyOrderModel> orderData;
	
	public static final HistoryOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HistoryOrderManagerImpl();
	}
	
	public static final HistoryOrderManager getInstance(){
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
	
	/**
	 * wrap into the observablelist
	 */
	@Override
	public ObservableList<MyOrderModel> getOrderList() {
		orderData = FXCollections.observableArrayList();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			orderData.add(new MyOrderModel(it.next()));
		return orderData;
	}

}
