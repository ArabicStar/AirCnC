package service.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.OrderDao;
import data.dao.UserDao;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import po.UserPo;
import po.order.OrderPo;
import service.order.OrderListingService;
import vo.order.OrderVo;

public class OrderListingServiceImpl implements OrderListingService {

	
	private int hotelId;
	
	private List<OrderPo> hotelOrderList;
	
	private OrderDao orderDao;
	
	private UserDao userDao;
	/**
	 * FIXME: 这个构造函数涉及到的地方特别多，注意修改用到OrderServiceImpl的地方
	 * @param hotelId
	 */
	public OrderListingServiceImpl(int hotelId){
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
		this.userDao = UserDaoImpl.getInstance();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}

	@Override
	public List<OrderVo> getAllOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			UserPo userPo = userDao.getUser(orderPo.getUserId());
			OrderVo orderVo = new OrderVo(orderPo, userPo);
			list.add(orderVo);
		}
		return list;
	}

	@Override
	public List<OrderVo> getUnfinishedOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 0){
				UserPo userPo = userDao.getUser(orderPo.getUserId());
				OrderVo orderVo = new OrderVo(orderPo, userPo);
				list.add(orderVo);
			}
		}
		return list;
	}

	@Override
	public List<OrderVo> getFinishedOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 1){
				UserPo userPo = userDao.getUser(orderPo.getUserId());
				OrderVo orderVo = new OrderVo(orderPo, userPo);
				list.add(orderVo);
			}
		}
		return list;
	}

	@Override
	public List<OrderVo> getAbnormalOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 2){
				UserPo userPo = userDao.getUser(orderPo.getUserId());
				OrderVo orderVo = new OrderVo(orderPo, userPo);
				list.add(orderVo);
			}
		}
		return list;
	}

}
