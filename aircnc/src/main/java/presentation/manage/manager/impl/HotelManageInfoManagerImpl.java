package presentation.manage.manager.impl;

import presentation.manage.manager.HotelManageInfoManager;
import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVo;

public class HotelManageInfoManagerImpl implements HotelManageInfoManager{
	
	private HotelVo vo;
	private HotelManageModel hotelInfo;
	
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
