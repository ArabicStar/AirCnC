package presentation.member.accessor.impl;

import presentation.member.accessor.RegisterMainAccessor;

public class RegisterMainAccessorImpl implements RegisterMainAccessor{

	private String username;
	
	private String password;
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setUsername(String name) {
		
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}
	
}
