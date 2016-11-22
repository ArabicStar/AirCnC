package service.impl.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import data.dao.OrderDao;
import data.dao.impl.OrderDaoImpl;
import po.order.OrderPo;
import service.order.OrderLogicService;

public class OrderLogicServiceImpl implements OrderLogicService {

	private int hotelId;

	private List<OrderPo> hotelOrderList;

	private OrderDao orderDao;

	/**
	 * 订单逻辑操作的接口实现类
	 * @param hotelId 酒店的Id
	 */
	public OrderLogicServiceImpl(int hotelId){
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
//		this.userDao = UserDaoImpl.getInstance();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}

	/**
	 * 执行订单
	 * @param orderId 订单的Id
	 * @return 成功执行则返回true，不成功则返回false
	 */
	@Override
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

	/**
	 * 延迟订单的执行时间
	 * @param orderId 订单的Id
	 * @return 成功延迟则返回true，不成功则返回false
	 * TODO:重写该方法
	 * 原因：这里的时间计算方法不一样
	 */
	@Override
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
	
	/**
	 * 取消订单
	 * @param orderId 订单的Id
	 * @return 成功取消则返回true，不成功则返回false
	 */
	@Override
	public boolean revealOrder(int orderId) {
		OrderPo orderPo = orderDao.getOrder(orderId);
		if(orderPo != null) {
			if(orderPo.getStatus() == 0) {
				orderPo.setStatus(3);
				// TODO:未处理信用值
			}
		}
		return false;
	}


}
