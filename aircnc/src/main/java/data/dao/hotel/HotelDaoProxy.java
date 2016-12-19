package data.dao.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.packedRmiEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.rmi.RemoteException;

import data.dao.rmi.hotel.RemoteHotelDao;
import po.hotel.HotelPo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class HotelDaoProxy extends AccessSecureProxy implements HotelDao {
	/**
	 * Singleton instance
	 */
	private static HotelDaoProxy instance;

	public static final HotelDaoProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelDaoProxy(clientId);
	}

	public static final HotelDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/**
	 * Actual hotel dao handler
	 */
	private RemoteHotelDao hotelDao;
	
	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	private HotelDaoProxy(Client clientId) {
		super(clientId);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Load a spefic hotel dao, auth to clients of hotel and manage, plus
	 * server.<br>
	 */
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE})
	public void loadRemoteHotelDao(RemoteHotelDao hotelDao){
		checkAuthentication();

		this.hotelDao = hotelDao;
	}


	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public HotelPo findHotelById(int id) {
		checkAuthentication();
		
		try {
			return hotelDao.findHotelById(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}
	
	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public HotelPo findHotelByName(String name) {
		checkAuthentication();
		
		try {
			return hotelDao.findHotelByName(name);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteHotel(int id) {
		checkAuthentication();
		
		try {
			return hotelDao.deleteHotel(id);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public boolean updateHotel(HotelPo po) {
		checkAuthentication();
		
		try {
			return hotelDao.updateHotel(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean addHotel(HotelPo po) {
		checkAuthentication();
		
		try {
			return hotelDao.addHotel(po);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}

	
	@Override
	@AuthenticatePolicy({ Client.HOTEL})
	public boolean existName(String name) {
		checkAuthentication();
		
		try {
			return hotelDao.existName(name);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw packedRmiEx(e);
		}
		
	}

}
