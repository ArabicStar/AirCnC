package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.SearchHotelInfoAccessor;
import utils.condition.Condition;
import utils.condition.ConditionBuilder;

public class SearchHotelInfoAccessorImpl implements SearchHotelInfoAccessor{
	
	private static SearchHotelInfoAccessor instance;
	
	private Condition scope;
	private String hotelName;
	
	public static final SearchHotelInfoAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SearchHotelInfoAccessorImpl();
	}
	
	public static final SearchHotelInfoAccessor getInstance(){
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
	public String getHotelName(){
		if(this.hotelName == null)
			throw accessorNotReadyEx();
		return this.hotelName;
	}
	
	@Override
	public Condition getScope(){
		if(scope == null)
			throw accessorNotReadyEx();
		return scope;
	}

	@Override
	public void setScope(String scope) {
		ConditionBuilder builder = new ConditionBuilder();
		builder.scopeLike(scope);
		this.scope = builder.buildCondition();
	}

	@Override
	public void setName(String name) {
		this.hotelName = name;
	}

}
