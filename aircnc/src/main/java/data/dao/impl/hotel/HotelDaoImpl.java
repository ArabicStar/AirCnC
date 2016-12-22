package data.dao.impl.hotel;

import static data.hibernate.Hibernator.execute;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import data.dao.hotel.HotelDao;
import data.dao.query.HotelQueryDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import po.member.MemberPo;
import utils.info.hotel.Room;

public enum HotelDaoImpl implements HotelDao, HotelQueryDao {
	INSTANCE;

	@Override
	public HotelPo findHotelById(final int id) {
		return execute(session -> session.get(HotelPo.class, id));
	}

	@Override
	public boolean deleteHotel(final int id) {
		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			HotelPo delHotel = (HotelPo) session.get(HotelPo.class, id);
			if (flag = Boolean.valueOf((delHotel != null)))// check existence
			{
				
				session.delete(delHotel);
			}
			return flag;
		});
	}

	@Override
	public boolean updateHotel(final HotelPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			HotelPo hotel = session.get(HotelPo.class, po.getId());
			if (flag = Boolean.valueOf(hotel != null))
				HotelPoBuilder.updatePo(po, hotel);

			return flag;
		});
	}

	@Override
	public boolean addHotel(final HotelPo po) {
		if (po == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;
			if (flag = Boolean.valueOf(session.createCriteria(HotelPo.class).add(Restrictions.eq("name", po.getName())).list().isEmpty()))
				// save HotelPo
				session.save(po);

			return flag;
		});
	}

	@Override
	public boolean existName(String name) {
		String tmp = name.replaceAll("(.{1})", "$1 ");
		return execute(session -> {
			return !session.createCriteria(HotelPo.class).add(Restrictions.eq("name", tmp)).list().isEmpty();
		});
	}

	@Override
	public HotelPo findHotelByName(String name) {

		String tmp = name.replaceAll("(.{1})", "$1 ");
		return execute(session -> {

			@SuppressWarnings("unchecked")
			List<HotelPo> hotels = (List<HotelPo>) session.createCriteria(HotelPo.class)
					.add(Restrictions.eq("name", tmp)).list();
			return hotels.size() == 0 ? null : hotels.get(0);
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
