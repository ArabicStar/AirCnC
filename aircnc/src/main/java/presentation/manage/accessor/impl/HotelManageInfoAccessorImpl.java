package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.manage.accessor.HotelManageInfoAccessor;
import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVoBuilder;

public class HotelManageInfoAccessorImpl implements HotelManageInfoAccessor{
	
	private static HotelManageInfoAccessor instance;
	
	private String id;
	private String name;
	private int passwordHash;
	private int star;
	
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
	public HotelVoBuilder getModifiedHotelVo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHotelId() {
		if(id == null)
			throw accessorNotReadyEx();
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}

	@Override
	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public void setHotelModel(HotelManageModel model) {
		// TODO Auto-generated method stub
		
	}

}
