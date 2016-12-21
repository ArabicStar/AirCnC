package data.dao.hotel;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.rmi.hotel.RemoteHotelDao;
import po.hotel.HotelPo;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;

public class HotelDaoProxy implements HotelDao {
	/**
	 * Singleton instance
	 */
	private static HotelDaoProxy instance;

	public static final HotelDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelDaoProxy();
	}

	public static final HotelDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	/**
	 * 'Default constructor, defines client identifier.<br>
	 * 
	 * @param clientId
	 */
	private HotelDaoProxy() {
	}

	/*
	 ****************************
	 ******* MarketDao*******
	 ****************************
	 */
	/**
	 * /*
	 ***************************
	 ******* MarketDao******
	 ***************************
	 */
	/**
	 * Actual market dao handler
	 */
	private RemoteHotelDao hotelDao;

	/**
	 * Load a specific market dao, auth to clients of user and market.<br>
	 * 
	 * @param marketDao
	 *            a specific market dao implementation
	 */
	public void loadRemoteHotelDao(RemoteHotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	@Override
	public boolean addHotel(HotelPo po) {
		return hazard(() -> {
			return hotelDao.addHotel(po);
		});
	}

	/* As follow are proxy methods. */
	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean deleteHotel(int id) {
		return hazard(() -> {
			return hotelDao.deleteHotel(id);
		});
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateHotel(HotelPo po) {
		return hazard(() -> {
			return hotelDao.updateHotel(po);
		});
	}
	
	@Override
	public HotelPo findHotelById(int id) {
		return hazard(() -> {
			return hotelDao.findHotelById(id);
		});
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.HOTEL })
	public boolean existName(String name) {
		return hazard(() -> {
			return hotelDao.existName(name);
		});
	}

	@Override
	public HotelPo findHotelByName(String name) {
		return hazard(() -> {
			return hotelDao.findHotelByName(name);
		});
	}

}
