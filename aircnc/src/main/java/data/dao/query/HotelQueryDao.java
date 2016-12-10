package data.dao.query;

import po.hotel.HotelPo;

public interface HotelQueryDao {
	public HotelPo searchById(int hotelId);

	public HotelPo searchByName(String name);

}
