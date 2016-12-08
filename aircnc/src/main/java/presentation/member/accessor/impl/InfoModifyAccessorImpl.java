package presentation.member.accessor.impl;

import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.model.MemberInfoModel;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * the content accessor of info modify of member info
 * @author paranoia
 *
 */
public class InfoModifyAccessorImpl implements InfoModifyAccessor{
	
	private String username;
	
	private String email;
	
	private String mobi;
	
	private String tele;
	
	private int passwordHash;
	
	private MemberInfoModel model;
	
	private MemberVo vo;

	@Override
	public MemberVo getModifiedMemberVo() {
		MemberVoBuilder builder = new MemberVoBuilder(vo.getType().toString()).setName(username)
				.setContactInfo(new ContactVoBuilder().setEmail(email).setMobilePhone(mobi)
						.setFixedPhone(tele).getContactInfo());
		vo = builder.getMemberInfo();
		return vo;
	}
	
	@Override
	public void setMemberModel(MemberInfoModel model){
		this.model = model;
	}
	
	@Override
	public void setName(String name){
		this.username = name;
	}
	
	@Override
	public void setMobi(String mobi){
		this.mobi = mobi;
	}
	
	@Override
	public void setTele(String tele){
		this.tele = tele;
	}
	
	@Override
	public void setPassword(String password){
		this.passwordHash = password.hashCode();
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

}
