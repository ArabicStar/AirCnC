package data.dao.order;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.rmi.order.RemoteOrderDao;
import po.order.OrderPo;

public class OrderDaoProxy implements OrderDao {
	/* Singleton */
	private static OrderDaoProxy instance;

	public static OrderDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderDaoProxy();
	}

	public static OrderDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private RemoteOrderDao dao;

	public void loadRemoteOrderDao(RemoteOrderDao dao) {
		this.dao = dao;
	}

	@Override
	public OrderPo getOrder(String orderId) {
		return hazard(() -> dao.getOrder(orderId));
	}

	@Override
	public boolean updateOrder(OrderPo orderPo) {
		return hazard(() -> dao.updateOrder(orderPo));
	}

	@Override
	public boolean addOrder(OrderPo orderPo) {
		return hazard(() -> dao.addOrder(orderPo));
	}

	@Override
	public boolean deleteOrder(String orderId) {
		return hazard(() -> dao.deleteOrder(orderId));
	}

	@Override
	public boolean existsOrder(String orderId) {
		return hazard(() -> dao.existsOrder(orderId));
	}

}
