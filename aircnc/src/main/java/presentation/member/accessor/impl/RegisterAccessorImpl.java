package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import static utils.exception.StaticExceptionFactory.builderNotReadyEx;

import presentation.member.accessor.RegisterAccessor;
import vo.member.MemberVoBuilder;

public class RegisterAccessorImpl implements RegisterAccessor{
	
	private static RegisterAccessor instance;
	
	private String username;

	private int passwordHash;
	
	private String type;
	
	private LocalDate birthday;
	
	private String enterprise;
	
	protected MemberVoBuilder builder;
	
	public static final RegisterAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new RegisterAccessorImpl();
	}
	
	public static final RegisterAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	@Override
	public MemberVoBuilder getNewAccountInfo() {
		switch(type){
		case "personal":
			builder = new MemberVoBuilder("PERSONAL").setName(username).setBirthday(birthday);
			break;
		case "business":
			builder = new MemberVoBuilder("BUSINESS").setName(username).setEnterprise(enterprise);
			break;
		default:
			throw builderNotReadyEx();
		}
		return builder;
	}

	@Override
	public int getPasswordHash() {
		return passwordHash;
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
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	
}
