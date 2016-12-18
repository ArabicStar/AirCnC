package service.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.query.OrderQueryDao;
import po.order.OrderPo;
import service.order.OrderListingService;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class OrderListingServiceImpl implements OrderListingService {

	private int hotelId;


	private OrderQueryDao orderQueryDao;


	/**
	 * 用于获取各种状态的订单的构造方法
	 * 
	 * @param hotelId
	 *            酒店的Id
	 */
	public OrderListingServiceImpl(int hotelId) {
		this.hotelId = hotelId;
		this.orderQueryDao = OrderDaoImpl.INSTANCE;
	}

	/**
	 * @param hotelId
	 *            酒店的Id
	 * @return 所有的订单
	 */
	@Override
	public List<OrderVo> getAllOrders() {
		List<OrderPo> list = orderQueryDao.searchByHotel(hotelId);
		List<OrderVo> result = new ArrayList<>();
		OrderPo po;
		for(int i = 0; i < list.size(); i++) {
			po = list.get(i);
			result.add(po.orderPo2Vo());
		}
//		System.out.println(list.size());
		
		return result;
	}

	/**
	 * @param hotelId
	 *            酒店的Id
	 * @return 未执行的订单
	 */
	@Override
	public List<OrderVo> getUnexecutedOrders() {
		return getOrdersByStatus(OrderStatus.UNEXECUTED);
	}

	private List<OrderVo> getOrdersByStatus(OrderStatus status) {
		List<OrderVo> result = new ArrayList<OrderVo>();
		List<OrderPo> list = orderQueryDao.searchByHotel(hotelId);
		OrderPo po;
		for(int i = 0; i < list.size(); i++) {
			po = list.get(i);
			if(po.getStatus() == status) {
				result.add(po.orderPo2Vo());
			}
		}
		return result;
	}
	
	/**
	 * @param hotelId
	 *            酒店的Id
	 * @return 已执行的订单
	 */
	@Override
	public List<OrderVo> getExecutedOrders() {
		return getOrdersByStatus(OrderStatus.EXECUTED);
	}

	/**
	 * @param hotelId
	 *            酒店的Id
	 * @return 异常的订单
	 */
	@Override
	public List<OrderVo> getAbnormalOrders() {
		return getOrdersByStatus(OrderStatus.ABNORMAL);
	}


}
