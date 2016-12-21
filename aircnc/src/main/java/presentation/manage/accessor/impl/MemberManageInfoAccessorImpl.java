package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import presentation.manage.accessor.MemberManageInfoAccessor;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class MemberManageInfoAccessorImpl implements MemberManageInfoAccessor{
	
	private static MemberManageInfoAccessor instance;
	
	private String id;
	private LocalDate birthday;
	private String enterprise;
	private MemberVo vo;
	
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
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	@Override
	public String getMemberId() {
		if(id == null)
			throw accessorNotReadyEx();
		return id;
	}
	
	@Override
	public MemberVo getModifiedMemberVo() {
		if(vo ==null)
			return null;
		
		MemberVo mem;
		if(vo.getType().toUpperCase().equals("BUSINESS"))
			mem = new MemberVoBuilder(vo).setEnterprise(enterprise).getMemberInfo();
		else
			mem = new MemberVoBuilder(vo).setBirthday(birthday).getMemberInfo();
		return mem;
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
	public void setMemberVo(MemberVo vo) {
		this.vo = vo;
	}

}
