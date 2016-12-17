package presentation.member.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.member.model.MyOrderModel;
import vo.order.OrderVo;

public interface MyOrderManager {
	
	public boolean setOrderList(List<OrderVo> list);
	
	public ObservableList<MyOrderModel> getOrderList();
}
