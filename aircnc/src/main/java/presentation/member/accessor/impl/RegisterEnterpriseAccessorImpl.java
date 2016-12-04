package presentation.member.accessor.impl;

import presentation.member.accessor.RegisterEnterpriseAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class RegisterEnterpriseAccessorImpl implements RegisterEnterpriseAccessor{
	
	private String username;
	
	private int passwordHash;
	
	private String enterprise;
	
	private MemberVo vo;
	
	@Override
	public MemberVo getEnterpriseMemberVo() {
		MemberVoBuilder builder = new MemberVoBuilder("BUSINESS").setName(username)
				.setContactInfo(new ContactVoBuilder().setEmail(null).setMobilePhone(null)
						.setFixedPhone(null).getContactInfo()).setEnterprise(enterprise);
		vo = builder.getMemberInfo();
		return vo;
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
	
}
