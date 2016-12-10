package service.query;

import vo.hotel.HotelVo;

public interface HotelQueryService {
	public HotelVo findById(int hotelId);

	public HotelVo findByName(String name);
}
