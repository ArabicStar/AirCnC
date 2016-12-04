package presentation.member.accessor.impl;

import java.time.LocalDate;

import presentation.member.accessor.RegisterPersonAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * 这个类还没写密码的传输
 * @author paranoia
 *
 */
public class RegisterPersonAccessorImpl implements RegisterPersonAccessor{
	
	private String username;
	
	private int passwordHash;
	
	private MemberVo vo;
	
	private LocalDate birthday;

	@Override
	public MemberVo getPersonMemberVo(){
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setName(username)
				.setContactInfo(new ContactVoBuilder().setEmail(null).setMobilePhone(null)
						.setFixedPhone(null).getContactInfo()).setBirthday(birthday);
		vo = builder.getMemberInfo();
		return vo;
	}
	
	@Override
	public void setUsername(String name){
		this.username = name;
	}
	
	@Override
	public void setPassword(String password){
		this.passwordHash = password.hashCode();
	}

	@Override
	public void setBirthday(LocalDate date) {
		this.birthday = date;	
	}
}
