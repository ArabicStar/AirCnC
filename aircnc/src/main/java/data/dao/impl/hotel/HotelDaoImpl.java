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
	public HotelPo findHotel(final int id) {
		return hiber.findHotel(id);
	}

	@Override
	public boolean deleteHotel(final int id) {
		return hiber.deleteHotel(id);
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
	public boolean existHotel(final int id) {
		return hiber.existId(id);
	}


	

}
