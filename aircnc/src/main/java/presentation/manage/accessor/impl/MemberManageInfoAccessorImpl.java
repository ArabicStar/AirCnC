package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import presentation.manage.accessor.MemberManageInfoAccessor;
import presentation.manage.model.MemberManageModel;
import vo.member.MemberVoBuilder;

public class MemberManageInfoAccessorImpl implements MemberManageInfoAccessor{
	
	private static MemberManageInfoAccessor instance;
	
	private String id;
	private LocalDate birthday;
	private String enterprise;
	
	public static final MemberManageInfoAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberManageInfoAccessorImpl();
	}
	
	public static final MemberManageInfoAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public String getMemberId() {
		if(id == null)
			throw accessorNotReadyEx();
		return id;
	}
	
	@Override
	public MemberVoBuilder getModifiedMemberVo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public void setMemberModel(MemberManageModel model) {
		// TODO Auto-generated method stub
		
	}

}
