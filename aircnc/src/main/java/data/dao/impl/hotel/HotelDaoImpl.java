package data.dao.impl.hotel;

import static data.hibernate.Hibernator.execute;

import data.dao.hotel.HotelDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;

public class HotelDaoImpl implements HotelDao{

	
	@Override
	public HotelPo findHotel(final String idString) {
		return execute(session -> {
			return (HotelPo) session.get(HotelPo.class, Integer.parseInt(idString));
		});
	}

	@Override
	public boolean deleteHotel(final String idString) {
		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			HotelPo deleted = (HotelPo) session.get(HotelPo.class, Integer.parseInt(idString));
			if (flag = Boolean.valueOf((deleted != null)))// check existence
				session.delete(deleted);

			return flag;
		});
	}

	@Override
	public boolean updateHotel(final HotelPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			HotelPo mem = session.get(HotelPo.class, Integer.parseInt(po.getStringId()));
			if (flag = Boolean.valueOf(mem != null))
				HotelPoBuilder.updatePo(po, mem);

			return flag;
		});
	}

	@Override
	public boolean addHotel(final HotelPo po) {
		if (po == null)
			return false;

		// should not exist yet
		if (existHotel(po.getStringId()))
			return false;

		return execute(session -> {
			// save HotelPo
			session.save(po);

			return true;
		});
	}

	@Override
	public boolean existHotel(final String idString) {
		return execute(session -> {
			return (HotelPo) session.get(HotelPo.class, Integer.parseInt(idString)) != null;
		});
	}


	

}
