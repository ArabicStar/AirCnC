package service.hotel;

import java.util.List;

import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public interface HotelOrderService {

	public boolean executeOrder(OrderInfo order);
	
	public boolean appealOrder(OrderInfo order);	
	
	public List<OrderVo> getHotelAllOrders(int id);

	public List<OrderVo> getHotelOrdersByStatus(int id, OrderStatus status);
}
