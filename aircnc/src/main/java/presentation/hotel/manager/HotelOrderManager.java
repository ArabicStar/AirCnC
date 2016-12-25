package presentation.hotel.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.hotel.model.OrderModel;
import vo.order.OrderVo;

public interface HotelOrderManager {
	
	public boolean setOrderList(List<OrderVo> list);
	
	public ObservableList<OrderModel> getCheckOrderList();
	
	public ObservableList<OrderModel> getOrderList();
	
	
}
