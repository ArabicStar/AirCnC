package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.manage.manager.MemberManageInfoManager;
import presentation.manage.model.MemberManageModel;
import vo.member.MemberVo;

public class MemberManageInfoImpl implements MemberManageInfoManager{	
	
	private static MemberManageInfoManager instance;
	
	private MemberVo user;
	private ObservableList<MemberManageModel> memberInfo;
	
	public static final MemberManageInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberManageInfoImpl();
	}
	
	public static final MemberManageInfoManager getInstance(){
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
	public ObservableList<MemberManageModel> getMemberInfo() {
		memberInfo = FXCollections.observableArrayList();
		memberInfo.add(new MemberManageModel(user));
		return memberInfo;
	}
}
