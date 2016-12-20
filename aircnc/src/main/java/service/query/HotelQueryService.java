package service.query;

import java.util.List;

import utils.condition.Condition;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;

public interface HotelQueryService {
	public HotelVo findById(int hotelId);

	public HotelVo findByName(String name);
	
	public List<HotelVo> findByCondition(Condition cond);
}
