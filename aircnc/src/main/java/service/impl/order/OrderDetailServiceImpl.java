package service.impl.order;

import java.util.List;

import data.dao.OrderDao;
import data.dao.impl.OrderDaoImpl;
import po.order.OrderPo;
import service.order.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	
	private int hotelId;
	
	private List<OrderPo> hotelOrderList;
	
	private OrderDao orderDao;
	
	/**
	 * FIXME: 这个构造函数涉及到的地方特别多，注意修改用到OrderServiceImpl的地方
	 * @param hotelId
	 */
	public OrderDetailServiceImpl(int hotelId){
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}

	@Override
	public int getOrderUser(int orderId) {
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getId() == orderId){
				return orderPo.getUserId();
			}
		}
		return -1;
	}

	@Override
	public int getOrderPrice(int orderId) {
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getId() == orderId){
				return orderPo.getPrice();
			}
		}
		return -1;
	}

}
