package presentation.hotel.accessor.impl;

import presentation.hotel.accessor.HotelLoginAccessor;

public class HotelLoginAccessorImpl implements HotelLoginAccessor{
	private String name;
	
	private int passwordHash;

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
