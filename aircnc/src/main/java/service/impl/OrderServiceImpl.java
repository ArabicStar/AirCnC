package service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.dao.OrderDao;
import data.dao.UserDao;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import data.datahelper.DataFactory;
import data.datahelper.impl.DataFactoryImpl;
import po.OrderPo;
import po.UserPo;
import service.OrderService;
import vo.OrderVo;

public class OrderServiceImpl implements OrderService{
	
	private int hotelId;
	
	private List<OrderPo> hotelOrderList;
	
	private OrderDao orderDao;
	
	private UserDao userDao;
	
	public OrderServiceImpl(int hotelId){
		this.hotelId = hotelId;
		orderDao = OrderDaoImpl.getInstance();
		userDao = UserDaoImpl.getInstance();
		hotelOrderList = orderDao.getOrders(hotelId);
	}

	public List<OrderVo> getAllOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			UserPo userPo = userDao.getUser(orderPo.getUserId());
			OrderVo orderVo = new OrderVo(orderPo, userPo);
			list.add(orderVo);
		}
		return list;
	}

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

	public int getOrderUser(int orderId) {
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getId() == orderId){
				return orderPo.getUserId();
			}
		}
		return -1;
	}

	public int getOrderPrice(int orderId) {
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getId() == orderId){
				return orderPo.getPrice();
			}
		}
		return -1;
	}

	public boolean finishOrder(int orderId) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if(orderPo != null){
			//检查订单状态是否为未执行
			if(orderPo.getStatus() == 0){
				//修改订单状态
				orderPo.setStatus(1);
				//设置订单执行时间
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String time=format.format(date);
				orderPo.setEntryTime(time);
				//修改订单
				if(orderDao.updateOrder(orderPo)){
					for (OrderPo o : hotelOrderList) {
						if(o.getId() == orderId){
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

	public boolean delayOrder(int orderId, String delayTime) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if(orderPo != null){
			//检查订单状态是否为异常订单
			if(orderPo.getStatus() == 2){
				//修改订单状态
				orderPo.setStatus(0);
				//修改订单最晚执行时间
				orderPo.setLastTime(delayTime);
				//修改订单
				if(orderDao.updateOrder(orderPo)){
					for (OrderPo o : hotelOrderList) {
						if(o.getId() == orderId){
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

	

}
