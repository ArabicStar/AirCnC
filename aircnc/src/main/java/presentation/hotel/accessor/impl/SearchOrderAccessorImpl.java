package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import presentation.hotel.accessor.SearchOrderAccessor;
import presentation.member.accessor.SearchOrderInfoAccessor;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class SearchOrderAccessorImpl implements SearchOrderAccessor{

	private static SearchOrderAccessor instance;
	
	private Set<OrderStatus> status;
	
	private OrderVo vo;
	
	public static final SearchOrderAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SearchOrderAccessorImpl();
	}
	
	public static final SearchOrderAccessor getInstance(){
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

	@Override
	public void setOrderVo(OrderVo vo) {
		this.vo = vo;
		
	}

	@Override
	public OrderVo getOrderVo() {
		return vo;		
	}

}
