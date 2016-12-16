package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.hotel.accessor.HotelLoginAccessor;

public class HotelLoginAccessorImpl implements HotelLoginAccessor{
	
	private static HotelLoginAccessor instance;
	
	private String name;
	
	private int passwordHash;
	
	public static final HotelLoginAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelLoginAccessorImpl();
	}

	public static final HotelLoginAccessor getInstance() {
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
	public String getName() {
		
		return name;
	}

	@Override
	public int getPasswordHash() {
		// TODO Auto-generated method stub
		return passwordHash;
	}
	
	@Override
	public void setDeliveredName(String name){
		this.name = name;
	}
	
	@Override
	public void setDeliveredPassword(String password){
		this.passwordHash = password.hashCode();
	}
}
