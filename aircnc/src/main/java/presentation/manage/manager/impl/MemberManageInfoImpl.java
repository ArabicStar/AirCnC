package presentation.manage.manager.impl;

import presentation.manage.manager.MemberManageInfoManager;
import presentation.manage.model.MemberManageModel;
import vo.member.MemberVo;

public class MemberManageInfoImpl implements MemberManageInfoManager{	

	private MemberVo user;
	private MemberManageModel memberInfo;
	
	@Override
	public boolean setUser(MemberVo vo){
		if(vo!=null){
			this.user = vo;
			return true;
		}
		return false;
	}

	/**
	 * wrap into the MemberInfoModel
	 */
	@Override
	public MemberManageModel getMemberInfo() {
		memberInfo = new MemberManageModel(user);
		return memberInfo;
	}
}
