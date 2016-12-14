package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.MyOrderManager;
import presentation.member.model.MyorderModel;
import vo.order.OrderVo;

/**
 * the manager of member info
 * aiming to receive the MemberVo from the logic layer
 * and deliver the member info model to the presentation layer
 * @author paranoia
 *
 */
public class MyOrderManagerImpl implements MyOrderManager{
	
	private static MyOrderManager instance;
	
	private List<OrderVo> orders;
	
	private ObservableList<MyorderModel> orderData;
	
	public static final MyOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MyOrderManagerImpl();
	}
	
	public static final MyOrderManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
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
	public ObservableList<MyorderModel> getOrderList() {
		orderData = FXCollections.observableArrayList();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			orderData.add(new MyorderModel(it.next()));
		return orderData;
	}

}
