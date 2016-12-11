package presentation.member.accessor.impl;

import presentation.member.accessor.RegisterEnterpriseAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

public class RegisterEnterpriseAccessorImpl implements RegisterEnterpriseAccessor {

	private String username;

	private int passwordHash;

	private String enterprise;

	private MemberVoBuilder builder;

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
