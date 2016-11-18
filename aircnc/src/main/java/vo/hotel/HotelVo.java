package vo.hotel;

import utils.info.hotel.HotelInfo;

public abstract class HotelVo extends HotelInfo{
	
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}
	
	HotelVo setID(String id){
		this.id = id;
		
		return this;
	}
}
