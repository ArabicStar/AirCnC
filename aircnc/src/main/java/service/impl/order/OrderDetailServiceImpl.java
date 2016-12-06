package service.impl.order;

import java.util.List;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import po.order.OrderPo;
import service.order.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

	private int hotelId;
	//
	private List<OrderPo> hotelOrderList;
	//
	private OrderDao orderDao;

	/**
	 * 获取订单详细信息的接口实现类<br>
	 * 负责返回订单的用户Id、订单的价格<br>
	 * 
	 * @param hotelId
	 *            酒店的Id
	 */
	public OrderDetailServiceImpl(int hotelId) {
		this.hotelId = hotelId;
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
		for (OrderPo orderPo : hotelOrderList) {
			if (orderPo.getOrderId() == orderId) {
				return orderPo.getUserId();
			}
		}
		return -1;
	}

	@Override
	public double getOrderPrice(String orderId) {
		for (OrderPo orderPo : hotelOrderList) {
			if (orderPo.getOrderId() == orderId) {
				return orderPo.getPrice();
			}
		}
		return -1;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

}
