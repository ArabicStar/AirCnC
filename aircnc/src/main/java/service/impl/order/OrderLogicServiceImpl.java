package service.impl.order;

import java.util.List;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import po.order.OrderPo;
import service.order.OrderLogicService;
import utils.info.order.OrderStatus;

public class OrderLogicServiceImpl implements OrderLogicService {

	private int hotelId;

	private List<OrderPo> hotelOrderList;

	private OrderDao orderDao;

	/**
	 * 订单逻辑操作的接口实现类
	 * 
	 * @param hotelId
	 *            酒店的Id
	 */
	public OrderLogicServiceImpl(int hotelId) {
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.INSTANCE;
		// this.userDao = UserDaoImpl.getInstance();
	}

	/**
	 * 执行订单
	 * 
	 * @param orderId
	 *            订单的Id
	 * @return 成功执行则返回true，不成功则返回false
	 */
	@Override
	public boolean finishOrder(String orderId) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if (orderPo != null) {
			// 检查订单状态是否为未执行
			if (orderPo.getStatus() == OrderStatus.UNEXECUTED) {
				// 修改订单状态
				orderPo.setStatus(OrderStatus.EXECUTED);
				// 设置订单执行时间
				// Date date=new Date();
				// DateFormat format=new SimpleDateFormat("yyyy/MM/dd
				// HH:mm:ss");
				// String time=format.format(date);
				/**
				 * FIXME:这里需要重新设定时间，因为把旧的时间改掉了
				 */
				orderPo.setEntryTime(null);
				// 修改订单
				if (orderDao.updateOrder(orderPo)) {
					for (OrderPo o : hotelOrderList) {
						if (o.getOrderId() == orderId) {
							o = orderPo;
							break;
						}
					}
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * 延迟订单的执行时间
	 * 
	 * @param orderId
	 *            订单的Id
	 * @return 成功延迟则返回true，不成功则返回false TODO:重写该方法 原因：这里的时间计算方法不一样
	 */
	@Override
	public boolean delayOrder(String orderId, String delayTime) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if (orderPo != null) {
			// 检查订单状态是否为异常订单
			if (orderPo.getStatus() == OrderStatus.ABNORMAL) {
				// 修改订单状态
				orderPo.setStatus(OrderStatus.UNEXECUTED);
				// 修改订单最晚执行时间
				/**
				 * FIXME:这里需要填上最晚执行时间，而不是填空
				 */
				orderPo.setLastTime(null);
				// 修改订单
				if (orderDao.updateOrder(orderPo)) {
					for (OrderPo o : hotelOrderList) {
						if (o.getOrderId() == orderId) {
							o = orderPo;
							break;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 取消订单
	 * 
	 * @param orderId
	 *            订单的Id
	 * @return 成功取消则返回true，不成功则返回false
	 */
	@Override
	public boolean repealOrder(String orderId) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if (orderPo != null) {
			if (orderPo.getStatus() == OrderStatus.UNEXECUTED) {
				orderPo.setStatus(OrderStatus.REPEALED);
				// TODO:未处理信用值
			}
		}
		return false;
	}

}
