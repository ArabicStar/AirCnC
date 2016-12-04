package presentation.member.accessor.impl;

import presentation.member.accessor.InfoModifyAccessor;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class InfoModifyContentAccessor implements InfoModifyAccessor{
	
	private String username;
	
	private String email;
	
	private String mobi;
	
	private String tele;
	
	private int passwordHash;
	
	private MemberVo vo;

	@Override
	public MemberVo getModifiedMemberVo() {
		MemberVoBuilder builder = new MemberVoBuilder(vo.getType().toString()).setName(username)
				.setContactInfo(new ContactVoBuilder().setEmail(email).setMobilePhone(mobi)
						.setFixedPhone(tele).getContactInfo());
		vo = builder.getMemberInfo();
		return vo;
	}
	
	public void setMemberVo(MemberVo vo){
		this.vo = vo;
	}
	
	public void setName(String name){
		this.username = name;
	}
	
	public void setMobi(String mobi){
		this.mobi = mobi;
	}
	
	public void setTele(String tele){
		this.tele = tele;
	}
	
	public void setPasswordHash(String password){
		this.passwordHash = password.hashCode();
	}

}
