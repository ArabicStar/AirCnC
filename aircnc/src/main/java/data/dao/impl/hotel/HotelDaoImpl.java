package data.dao.impl.hotel;

import static data.hibernate.Hibernator.execute;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import data.dao.hotel.HotelDao;
import data.dao.query.HotelQueryDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;

public enum HotelDaoImpl implements HotelDao, HotelQueryDao {
	INSTANCE;
	
	@Override
	public HotelPo findHotelById(final int id) {
		return execute(session -> {
			return (HotelPo) session.get(HotelPo.class, id);
		});
	}

	@Override
	public boolean deleteHotel(final String name) {
		return execute(session -> {
			@SuppressWarnings("unchecked")
			List<HotelPo> hotels = (List<HotelPo>) session.createCriteria(HotelPo.class)
					.add(Restrictions.eq("name", name)).list();
			if (hotels.size() == 0) {
				return false;
			} else {
				session.delete(hotels.get(0));
				return true;
			}
		});
	}

	@Override
	public boolean updateHotel(final HotelPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			HotelPo mem = session.get(HotelPo.class, po.getId());
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
		if (existName(po.getName()))
			return false;

		return execute(session -> {
			// save HotelPo
			session.save(po);

			return true;
		});
	}

	@Override
	public boolean existName(String name) {
		return execute(session -> {
			return !session.createCriteria(HotelPo.class).add(Restrictions.eq("name", name)).list().isEmpty();
		});
	}

	@Override
	public HotelPo findHotelByName(String name) {
		return execute(session -> {
			@SuppressWarnings("unchecked")
			List<HotelPo> hotels = (List<HotelPo>) session.createCriteria(HotelPo.class)
					.add(Restrictions.eq("name", name)).list();
			if (hotels.size() == 0) {
				return null;
			} else {
				return hotels.get(0);
			}
		});
	}

	@Override
	public HotelPo searchById(int hotelId) {
		return findHotelById(hotelId);
	}

	@Override
	public HotelPo searchByName(String name) {
		return findHotelByName(name);
	}

	@Override
	public List<HotelPo> searchByCriteria(DetachedCriteria dc) {
		return execute(session -> {
			@SuppressWarnings("unchecked")
			List<HotelPo> list = (List<HotelPo>) dc.getExecutableCriteria(session).list();
			return list;
		});
	}

}
