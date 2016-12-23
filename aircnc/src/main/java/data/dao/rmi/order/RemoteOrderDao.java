package data.dao.rmi.order;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.order.OrderPo;

public interface RemoteOrderDao extends Remote {
	public OrderPo getOrder(String orderId) throws RemoteException;

	public boolean updateOrder(OrderPo orderPo) throws RemoteException;

	public boolean addOrder(OrderPo orderPo) throws RemoteException;

	public boolean deleteOrder(String orderId) throws RemoteException;

	public boolean existsOrder(String orderId) throws RemoteException;
}
