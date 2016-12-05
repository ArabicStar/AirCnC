package data.dao.impl.order;

import static data.hibernate.Hibernator.execute;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import data.dao.order.OrderDao;
import po.order.OrderPo;

public enum OrderDaoImpl implements OrderDao {
	INSTANCE;

	public static OrderDaoImpl getInstance() {
		return INSTANCE;
	}

	private OrderDaoImpl() {

	}

	public OrderPo getOrder(String orderId) {
		return execute(session -> {
			return (OrderPo) session.get(OrderPo.class, orderId);
		});
	}

	public List<OrderPo> getOrders(int hotelId) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		String hql = "from OrderPo where HOTELID = ?";

		return execute(session -> {
			Query query = session.createQuery(hql);
			query.setString(0, Integer.toString(hotelId));
			@SuppressWarnings("unchecked")
			List<OrderPo> list = query.list();
			return list;
		});
	}

	public boolean updateOrder(OrderPo orderPo) {
		if (orderPo == null) {
			return false;
		}
		if (!existsOrder(orderPo.getOrderId())) {
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
		if (!existsOrder(orderId)) {
			return false;
		}
		
		return execute(session -> {
			OrderPo deleted = session.get(OrderPo.class, orderId);
			session.delete(deleted);
			return true;
		});
	}

}
