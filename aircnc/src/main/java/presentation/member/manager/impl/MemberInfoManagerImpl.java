package presentation.member.manager.impl;

import presentation.member.manager.MemberInfoManager;
import vo.member.MemberVo;

public class MemberInfoManagerImpl implements MemberInfoManager{
	
	private MemberVo user;
	
	@Override
	public boolean setUser(MemberVo vo){
		if(vo!=null){
			this.user = vo;
			return true;
		}
		return false;
	}
	
	@Override
	public String getUsername(){
		return user.getName();
	}
	
	@Override
	public String getTele(){
		return user.getContact().getMobilePhone();
	}
	
	@Override
	public String getMobi(){
		return user.getContact().getMobilePhone();
	}
	
	@Override
	public String getEmail(){
		return user.getContact().getEmail();
	}
	
	@Override
	public int getCredit(){
		return user.getCredit();
	}

	@Override
	public MemberVo getMemberVo() {
		return user;
	}
}
