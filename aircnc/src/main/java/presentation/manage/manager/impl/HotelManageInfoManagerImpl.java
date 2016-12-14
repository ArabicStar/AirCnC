package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.manage.manager.HotelManageInfoManager;
import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVo;

public class HotelManageInfoManagerImpl implements HotelManageInfoManager{
	
	private static HotelManageInfoManager instance;
	
	private HotelVo vo;
	private HotelManageModel hotelInfo;
	
	public static final HotelManageInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelManageInfoManagerImpl();
	}
	
	public static final HotelManageInfoManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public boolean setHotel(HotelVo vo){
		if(vo!=null){
			this.vo = vo;
			return true;
		}
		return false;
	}

	/**
	 * wrap into the HotelManageModel
	 */
	@Override
	public HotelManageModel getHotelInfo() {
		hotelInfo = new HotelManageModel(vo);
		return hotelInfo;
	}

}
