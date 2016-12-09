package presentation.hotel.manager.impl;

import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;
import vo.hotel.HotelVo;

public class InfoManagerImpl implements InfoManager {

	private HotelVo hotel;
	private HotelInfoModel hotelInfo;
	
	@Override
	public boolean setHotel(HotelVo vo){
		if(vo!=null){
			this.hotel = vo;
			return true;
		}
		return false;
	}

	/**
	 * wrap into the HotelInfoModel
	 */
	@Override
	public HotelInfoModel getHotelInfo() {
		hotelInfo = new HotelInfoModel(hotel);
		return hotelInfo;
	}

}
