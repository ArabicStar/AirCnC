package presentation.member.manager.impl;

import presentation.member.manager.UserInfoManager;
import presentation.member.model.MemberInfoModel;
import utils.info.member.MemberInfo;

/**
 * the manager of member info aiming to receive the MemberVo from the logic
 * layer and deliver the member info model to the presentation layer
 * 
 * @author paranoia
 *
 */
public class MemberInfoManagerImpl implements UserInfoManager {

	private MemberInfo user;
	private MemberInfoModel memberInfo;

	@Override
	public boolean setUser(MemberInfo vo) {
		if (vo != null) {
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
