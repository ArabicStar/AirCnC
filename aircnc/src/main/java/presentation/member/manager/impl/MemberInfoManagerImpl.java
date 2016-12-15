package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

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
	
	private static UserInfoManager instance;
	
	private MemberInfo user;
	private MemberInfoModel memberInfo;

	public static final UserInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberInfoManagerImpl();
	}
	
	public static final UserInfoManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
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
