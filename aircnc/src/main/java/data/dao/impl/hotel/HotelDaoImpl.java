package data.dao.impl.hotel;

import data.dao.hotel.HotelDao;
import data.hibernate.HotelHibernator;
import po.hotel.HotelPo;
import utils.info.hotel.HotelInfoTemplate;

public class HotelDaoImpl implements HotelDao{

	private HotelHibernator hiber;
	
	public HotelDaoImpl(HotelHibernator hiber) {
		this.hiber = hiber;
	}
	
	@Override
	public HotelPo findHotel(final String id) {
		return hiber.findHotel(parseId(id));
	}

	@Override
	public boolean deleteHotel(final String id) {
		return hiber.deleteHotel(parseId(id));
	}

	@Override
	public boolean updateHotel(final HotelPo po) {
		if (po == null)
			return false;

		return hiber.updateHotel(po);
	}

	@Override
	public boolean addHotel(final HotelPo po) {
		if (po == null)
			return false;

		return hiber.addHotel(po);
	}

	@Override
	public boolean existHotel(final String id) {
		return hiber.existId(parseId(id));
	}
	
	// parse an id string. if invalid, throw IAE.
	private static final int parseId(final String id) {
		if (!HotelInfoTemplate.checkID(id))
			throw new IllegalArgumentException("Wrong ID");

		return Integer.parseInt(id);
	}	

	

}
