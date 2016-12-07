package presentation.hotel.accessor.impl;

import presentation.hotel.accessor.HotelLoginAccessor;

public class HotelLoginAccessorImpl implements HotelLoginAccessor{
	private String id;
	
	private int passwordHash;

	@Override
	public String getId() {
		
		return id;
	}

	@Override
	public int getPasswordHash() {
		// TODO Auto-generated method stub
		return passwordHash;
	}
	
	@Override
	public void setDeliveredId(String id){
		this.id = id;
	}
	
	@Override
	public void setDeliveredPassword(String password){
		this.passwordHash = password.hashCode();
	}
}
