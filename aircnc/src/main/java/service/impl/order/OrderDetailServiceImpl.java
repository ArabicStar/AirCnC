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
	 * 获取订单详细信息的接口实现类<br>
	 * 负责返回订单的用户Id、订单的价格<br>
	 * @param hotelId 酒店的Id
	 */
	public OrderDetailServiceImpl(int hotelId){
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}


	/**
	 * @param orderId 订单的Id
	 * @return 返回订单的用户Id，查找失败则返回-1
	 * FIXME:返回值类型应该为String {@link utils.info.order.OrderInfoTemplate}
	 */
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
