package presentation.member.manager.impl;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.MyOrderManager;
import presentation.member.model.MyorderModel;
import vo.order.OrderVo;

public class MyOrderManagerImpl implements MyOrderManager{
	
	private List<OrderVo> orders;
	
	private ObservableList<MyorderModel> orderData;
	
	@Override
	public boolean setOrderList(List<OrderVo> list) {
		if(list!=null){
			this.orders = list;
			return true;
		}
		return false;
	}

	@Override
	public ObservableList<MyorderModel> getOrderList() {
		orderData = FXCollections.observableArrayList();
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext())
			orderData.add(new MyorderModel(it.next()));
		return orderData;
	}

}
