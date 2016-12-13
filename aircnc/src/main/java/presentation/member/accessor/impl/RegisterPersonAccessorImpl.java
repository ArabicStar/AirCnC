package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import presentation.member.accessor.RegisterPersonAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

/**
 * 
 * the accessor of register info
 * @author paranoia
 *
 */
public class RegisterPersonAccessorImpl implements RegisterPersonAccessor {
	
	private static RegisterPersonAccessor instance;
	
	private String username;

	private int passwordHash;

	private MemberVoBuilder builder;

	private LocalDate birthday;

	public static final RegisterPersonAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new RegisterPersonAccessorImpl();
	}
	
	public static final RegisterPersonAccessor getInstance(){
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
	public void setBirthday(LocalDate date) {
		this.birthday = date;
	}

	@Override
	public MemberVoBuilder getNewAccountInfo() {
		builder = new MemberVoBuilder("PERSONAL").setName(username)
				.setContactInfo(new ContactVoBuilder().getContactInfo()).setBirthday(birthday);
		return builder;
	}

	@Override
	public int getPasswordHash() {
		return passwordHash;
	}
}
