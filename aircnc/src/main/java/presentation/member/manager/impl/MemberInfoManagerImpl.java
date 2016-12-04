package presentation.member.manager.impl;

import presentation.member.manager.UserInfoManager;
import presentation.member.model.MemberInfoModel;
import vo.member.MemberVo;

/**
 * the manager of member info
 * aiming to receive the MemberVo from the logic layer
 * and deliver the member info model to the presentation layer
 * @author paranoia
 *
 */
public class MemberInfoManagerImpl implements UserInfoManager{
	
	private MemberVo user;
	private MemberInfoModel memberInfo;
	
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
	public MemberInfoModel getMemberInfo() {
		memberInfo = new MemberInfoModel(user);
		return memberInfo;
	}
}
