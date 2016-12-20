package interactor.hotel;

import java.util.List;

import vo.hotel.HotelVo;

public interface HotelSearchInteractor {
	public List<HotelVo> searchById();
	
	public List<HotelVo> searchByCondition();
}
