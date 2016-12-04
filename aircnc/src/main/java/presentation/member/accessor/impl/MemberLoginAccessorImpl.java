package presentation.member.accessor.impl;

import presentation.member.accessor.MemberLoginAccessor;

public class MemberLoginAccessorImpl implements MemberLoginAccessor{
	
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
