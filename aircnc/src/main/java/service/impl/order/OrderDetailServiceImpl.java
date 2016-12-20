package service.impl.order;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import po.order.OrderPo;
import service.order.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	
	private OrderDao orderDao;

	/**
	 * 获取订单详细信息的接口实现类<br>
	 * 负责返回订单的用户Id、订单的价格<br>
	 * 
	 * @param hotelId
	 *            酒店的Id
	 */
	public OrderDetailServiceImpl() {
		this.orderDao = OrderDaoImpl.INSTANCE;
	}

	/**
	 * @param orderId
	 *            订单的Id
	 * @return 返回订单的用户Id，查找失败则返回-1 传入值类型应该为String
	 *         {@link utils.info.order.OrderInfoTemplate}
	 */
	@Override
	public int getOrderUser(String orderId) {
		int userId = -1;
		userId = orderDao.getOrder(orderId).getUserId();
		return userId;
	}

	@Override
	public double getOrderOriginalPrice(String orderId) {
		double price = -1;
		price = orderDao.getOrder(orderId).getOriginalPrice();
		return price;
	}

	@Override
	public OrderPo getOrderInfoById(String orderId) {
		OrderPo info = orderDao.getOrder(orderId);
		return info;
	}

	@Override
	public boolean saveOrder(OrderPo orderPo) {
		try {
			orderDao.addOrder(orderPo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
