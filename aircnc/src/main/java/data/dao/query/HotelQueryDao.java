package data.dao.query;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import po.hotel.HotelPo;

public interface HotelQueryDao {
	public HotelPo searchById(int hotelId);

	public HotelPo searchByName(String name);

	public List<HotelPo> searchByCriteria(DetachedCriteria dc);
}
