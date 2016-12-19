package service.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import data.dao.hotel.HotelDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.manage.ManageHotelService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class ManageHotelManager implements ManageHotelService{

	private static ManageHotelService instance;

	public static ManageHotelService launch(HotelDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new ManageHotelManager(dao);
		return instance;
	}

	public static ManageHotelService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private HotelDao dao;
	
	private ManageHotelManager(HotelDao dao) {
		this.dao = dao;
	}
	
	@Override
	public HotelVo AddHotelInfo(HotelVoBuilder newHotelInfo, int passwordHash) {
		if (newHotelInfo == null)
			return null;


		// set id and password, build po
		HotelVo newHotelVo = newHotelInfo.getHotelInfo();
		HotelPo newHotelPo = new HotelPoBuilder(newHotelVo).setPasswordHash(passwordHash).getHotelInfo();

		// add new hotel
		boolean result = dao.addHotel(newHotelPo);
		if (result)
			return newHotelVo;

		return HotelVoBuilder.invalidInfo();
	}

	@Override
	public boolean ModifyHotelInfo(HotelInfo hotelInfo) {
		HotelPo po = dao.findHotelById(hotelInfo.getId());

		if (hotelInfo.getId() != po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel Info");

		return dao
				.updateHotel(new HotelPoBuilder(hotelInfo).setPasswordHash(po.getPasswordHash()).getHotelInfo());
	}

	@Override
	public HotelInfo getHotelInfo(String name) {
		if (dao == null)
			throw unsupportedOpEx("get hotel info");

		final HotelPo po = dao.findHotelByName(name);

		return po == null ? null : dao.findHotelByName(name);
	}

	@Override
	public boolean deleteHotelInfo(int id) {
		if (!HotelInfo.checkID(id))
			return false;
		//这里有问题！！！！！！！！！！！！！！！
		return dao.deleteHotel(String.valueOf(id));
	}

}
