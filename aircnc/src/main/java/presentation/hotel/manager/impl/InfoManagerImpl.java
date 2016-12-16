package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;
import vo.hotel.HotelVo;

public class InfoManagerImpl implements InfoManager {
	private static InfoManager instance;
	
	private HotelVo hotel;
	private HotelInfoModel hotelInfo;
	
	public static final InfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new InfoManagerImpl();
	}
	
	public static final InfoManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
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
