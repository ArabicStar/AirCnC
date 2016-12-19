package data.dao.rmi.hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.hotel.HotelPo;

public interface RemoteHotelDao extends Remote{
	
	public HotelPo findHotelById(final int id) throws RemoteException;
	
	public HotelPo findHotelByName(final String name) throws RemoteException;

	public boolean deleteHotel(final int id) throws RemoteException;

	public boolean updateHotel(final HotelPo po) throws RemoteException;

	public boolean addHotel(final HotelPo po) throws RemoteException;

	public boolean existName(final String name) throws RemoteException;
}
