package presentation.market.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.market.model.OrderModel;
import vo.order.OrderVo;

public interface UnexecutedOrderManager {
	
public void setUnexecutedOrders(List<OrderVo> orders);
	
	public ObservableList<OrderModel> getOrderList();
}
