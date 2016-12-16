package data.dao.rmi.query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.order.OrderPo;
import utils.info.order.OrderStatus;

public interface RemoteOrderQueryDao extends Remote {
	public List<OrderPo> searchByMember(String memberId) throws RemoteException;

	public List<OrderPo> searchByHotel(int hotelId) throws RemoteException;

	public List<OrderPo> searchByStatus(OrderStatus status) throws RemoteException;
}
