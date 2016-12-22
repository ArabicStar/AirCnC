package service.impl.order;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.order.OrderDao;
import service.order.OrderInfoService;
import utils.info.order.OrderInfo;

public class OrderInfoManager implements OrderInfoService {
	/* Singleton */
	private static OrderInfoManager instance;

	public static OrderInfoManager launch(OrderDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderInfoManager(dao);
	}

	public static OrderInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param dao
	 */
	private OrderInfoManager(OrderDao dao) {
		super();
		this.dao = dao;
	}

	private OrderDao dao;

	@Override
	public OrderInfo findOrder(String orderId) {
		if (orderId == null)
			return null;

		if (!OrderInfo.checkOrderId(orderId))
			return null;

		return dao.getOrder(orderId);
	}

}
