package data.dao.impl.order;

import static data.hibernate.Hibernator.execute;

import java.util.ArrayList;
import java.util.List;

import data.dao.order.OrderDao;
import po.order.OrderPo;

public class OrderDaoImpl implements OrderDao{
	
	private static OrderDaoImpl orderDataServiceImpl;
	
	public static OrderDaoImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDaoImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDaoImpl(){

	}

	public OrderPo getOrder(String orderId) {
		return  execute(session -> {
			return (OrderPo) session.get(OrderPo.class, orderId);
		});
	}

	public List<OrderPo> getOrders(int hotelId) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();

		return orderList;
	}

	public boolean updateOrder(OrderPo orderPo) {
		if (orderPo == null) {
			return false;
		}
		if(!existsOrder(orderPo.getOrderId())) {
			return false;
		}

		return execute(session -> {
			session.update(orderPo);
			return true;
		});
		
	}

	public boolean addOrderPo(OrderPo orderPo) {
		if (orderPo == null) {
			return false;
		}
		// should not exist yet
		if (existsOrder(orderPo.getOrderId())) {
			return false;
		}
		return execute(session -> {
			// save OrderPo
			session.save(orderPo);
			return true;
		});
	}
	
	public boolean existsOrder(String orderId) {
		return execute(session -> {
			return (OrderPo) session.get(OrderPo.class, orderId) != null;
		});
	}

	public boolean deleteOrderPo(String orderId) {
		// The order that exists can be deleted
		if(!existsOrder(orderId)) {
			return false;
		}
		OrderPo deleted = getOrder(orderId);
		return execute(session -> {
			session.delete(deleted);
			return true;
		});
	}

}
