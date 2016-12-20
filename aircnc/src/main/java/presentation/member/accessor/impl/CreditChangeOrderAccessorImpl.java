package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.CreditChangeOrderAccessor;

public class CreditChangeOrderAccessorImpl implements CreditChangeOrderAccessor{

	private static CreditChangeOrderAccessor instance;
	
	private String id;
	
	public static final CreditChangeOrderAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new CreditChangeOrderAccessorImpl();
	}
	
	public static final CreditChangeOrderAccessor getInstance(){
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
	public String getCauseId() {
		return id;
	}

	@Override
	public void setCauseId(String id) {
		this.id = id;
	}

}
