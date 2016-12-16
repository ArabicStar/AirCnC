package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import presentation.member.accessor.SearchOrderInfoAccessor;
import utils.info.order.OrderStatus;

public class SearchOrderInfoAccessorImpl implements SearchOrderInfoAccessor{
	
	private static SearchOrderInfoAccessor instance;
	
	private Set<OrderStatus> status;
	
	public static final SearchOrderInfoAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SearchOrderInfoAccessorImpl();
	}
	
	public static final SearchOrderInfoAccessor getInstance(){
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
	public Set<OrderStatus> getStatus() {
		if(status == null)
			throw accessorNotReadyEx();
		return status;
	}

	@Override
	public void setSearchTarget(Set<OrderStatus> target) {
		this.status = target;
		
	}
	

}
