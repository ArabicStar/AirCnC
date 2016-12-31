package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.manage.accessor.HotelManageInfoAccessor;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class HotelManageInfoAccessorImpl implements HotelManageInfoAccessor{
	
	private static HotelManageInfoAccessor instance;
	
	private int id;
	private String name;
	private int star;
	private int passwordHash;
	private HotelVo vo;
	
	public static final HotelManageInfoAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelManageInfoAccessorImpl();
	}
	
	public static final HotelManageInfoAccessor getInstance(){
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
	public HotelVo getModifiedHotelVo() {
		if(vo == null)
			return null;
		
		HotelVo hotel = new HotelVoBuilder(vo).setStar(star).setName(name).getHotelInfo();		
		return hotel;
	}

	@Override
	public int getHotelId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public void setHotelVo(HotelVo vo) {
		this.vo = vo;
	}

	@Override
	public void deleteHotel(int id) {
		this.id = id;
	}

	@Override
	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}
	
	@Override
	public int getPasswordHash() {
		return passwordHash;
	}

	@Override
	public String getHotelName() {
		return name;
	}

	@Override
	public HotelVo getAddedHotelVo() {
		HotelVo hotel = new HotelVoBuilder().setStar(star).setName(name).getHotelInfo();		
		return hotel;
	}


}
