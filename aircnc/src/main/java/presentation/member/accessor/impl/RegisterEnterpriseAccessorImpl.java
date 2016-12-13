package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.RegisterEnterpriseAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

public class RegisterEnterpriseAccessorImpl implements RegisterEnterpriseAccessor {
	
	private static RegisterEnterpriseAccessor instance;
	
	private String username;

	private int passwordHash;

	private String enterprise;

	private MemberVoBuilder builder;
	
	public static final RegisterEnterpriseAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new RegisterEnterpriseAccessorImpl();
	}
	
	public static final RegisterEnterpriseAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public void setUsername(String name) {
		this.username = name;
	}

	@Override
	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}

	@Override
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public MemberVoBuilder getNewAccountInfo() {
		builder = new MemberVoBuilder("BUSINESS").setName(username)
				.setContactInfo(new ContactVoBuilder().getContactInfo()).setEnterprise(enterprise);
		return builder;
	}

	@Override
	public int getPasswordHash() {
		return passwordHash;
	}

}
