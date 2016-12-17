package data.dao.rmi.query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import po.hotel.HotelPo;

public interface RemoteHotelQueryDao extends Remote {
	public HotelPo searchById(int hotelId) throws RemoteException;

	public HotelPo searchByName(String name) throws RemoteException;

	public List<HotelPo> searchByCriteria(DetachedCriteria dc) throws RemoteException;
}
