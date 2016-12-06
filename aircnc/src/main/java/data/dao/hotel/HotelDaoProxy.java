package data.dao.hotel;

import po.hotel.HotelPo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class HotelDaoProxy extends AccessSecureProxy implements HotelDao {
	/**
	 * Actual hotel dao handler
	 */
	private HotelDao hotelDao;
	
	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	protected HotelDaoProxy(Client clientId) {
		super(clientId);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Load actual hotel dao, auth to cilents of hotel and market
	 */
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE})
	public abstract void loadHotelDao();
	
	/**
	 * Load a spefic member dao, for convinience of pontential change.<br>
	 * 
	 * @param memberDao
	 *            a specific member dao implemention
	 */
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public void loadHotelDao(HotelDao hotelDao) {
		checkAuthentication();

		this.hotelDao = hotelDao;

	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public HotelPo findHotel(int id) {
		checkAuthentication();
		
		return hotelDao.findHotel(id);
	}
	
	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public HotelPo findHotel(String name) {
		checkAuthentication();
		
		return hotelDao.findHotel(name);
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteHotel(int id) {
		checkAuthentication();
		
		return hotelDao.deleteHotel(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public boolean updateHotel(HotelPo po) {
		checkAuthentication();
		
		return hotelDao.updateHotel(po);
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean addHotel(HotelPo po) {
		checkAuthentication();
		
		return hotelDao.addHotel(po);
	}

	
	@Override
	@AuthenticatePolicy({ Client.HOTEL})
	public boolean existName(String name) {
		checkAuthentication();
		
		return hotelDao.existName(name);
	}

}
