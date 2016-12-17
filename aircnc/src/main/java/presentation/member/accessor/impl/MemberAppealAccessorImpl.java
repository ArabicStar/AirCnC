package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.MemberAppealAccessor;

public class MemberAppealAccessorImpl implements MemberAppealAccessor{
	
	private static MemberAppealAccessor instance;
	
	private String appeal;
	private String id;
	
	public static final MemberAppealAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberAppealAccessorImpl();
	}
	
	public static final MemberAppealAccessor getInstance(){
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
	public String getAppeal() {
		if(appeal == null)
			throw accessorNotReadyEx();
		return appeal;
	}

	@Override
	public void setAppeal(String content) {
		this.appeal = content;
	}

	@Override
	public String getId() {
		if(id == null)
			throw accessorNotReadyEx();
		return id;
	}

	@Override
	public void setOrderId(String id) {
		this.id = id;
	}

}
