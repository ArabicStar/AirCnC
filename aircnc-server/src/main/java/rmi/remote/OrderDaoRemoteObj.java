package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import data.dao.rmi.order.RemoteOrderDao;
import po.order.OrderPo;
import rmi.RemoteHelper;

public class OrderDaoRemoteObj extends UnicastRemoteObject implements RemoteOrderDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8507620937828623925L;
	/* Singleton */
	private static OrderDaoRemoteObj instance;

	public static void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final OrderDao dao = OrderDaoImpl.INSTANCE;

		instance = new OrderDaoRemoteObj(dao);

		RemoteHelper.bindRemoteObj("RemoteOrderDao", instance);
	}
	/* Singleton */

	protected OrderDaoRemoteObj(OrderDao dao) throws RemoteException {
		super();
		this.orderDao = dao;
	}

	private OrderDao orderDao;

	@Override
	public OrderPo getOrder(String orderId) throws RemoteException {
		return orderDao.getOrder(orderId);
	}

	@Override
	public boolean updateOrder(OrderPo orderPo) throws RemoteException {
		return orderDao.updateOrder(orderPo);
	}

	@Override
	public boolean addOrder(OrderPo orderPo) throws RemoteException {
		return orderDao.addOrder(orderPo);
	}

	@Override
	public boolean deleteOrder(String orderId) throws RemoteException {
		return orderDao.deleteOrder(orderId);
	}

	@Override
	public boolean existsOrder(String orderId) throws RemoteException {
		return orderDao.existsOrder(orderId);
	}

}
