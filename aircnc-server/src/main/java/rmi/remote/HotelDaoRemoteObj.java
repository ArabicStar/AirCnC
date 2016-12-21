package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.hotel.HotelDao;
import data.dao.impl.hotel.HotelDaoImpl;
import data.dao.rmi.hotel.RemoteHotelDao;
import po.hotel.HotelPo;
import rmi.RemoteHelper;

public class HotelDaoRemoteObj extends UnicastRemoteObject implements RemoteHotelDao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HotelDaoRemoteObj instance;

	public static final void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final HotelDao hotelDao = HotelDaoImpl.INSTANCE;

		instance = new HotelDaoRemoteObj(hotelDao);

		RemoteHelper.bindRemoteObj("RemoteHotelDao", instance);
	}
	
	private HotelDao hotelDao;

	private HotelDaoRemoteObj(HotelDao hotelDao) throws RemoteException {
		super();
		this.hotelDao = hotelDao;
	}
	
	@Override
	public HotelPo findHotelById(int id) throws RemoteException {
		return this.hotelDao.findHotelById(id);
	}

	@Override
	public HotelPo findHotelByName(String name) throws RemoteException {
		return this.hotelDao.findHotelByName(name);
	}

	@Override
	public boolean deleteHotel(int id) throws RemoteException {
		return this.hotelDao.deleteHotel(id);
	}

	@Override
	public boolean updateHotel(HotelPo po) throws RemoteException {
		return this.hotelDao.updateHotel(po);
	}

	@Override
	public boolean addHotel(HotelPo po) throws RemoteException {
		return this.hotelDao.addHotel(po);
	}

	@Override
	public boolean existName(String name) throws RemoteException {
		return this.hotelDao.existName(name);
	}

}
