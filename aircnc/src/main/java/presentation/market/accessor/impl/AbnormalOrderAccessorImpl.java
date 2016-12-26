package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.market.accessor.AbnormalOrderAccessor;
import vo.order.OrderVo;

public class AbnormalOrderAccessorImpl implements AbnormalOrderAccessor{
	private static AbnormalOrderAccessor instance;
	
	private OrderVo order;
	
	public static final AbnormalOrderAccessor launch() {
		if (instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new AbnormalOrderAccessorImpl();
	}
	
	public static final AbnormalOrderAccessor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}
	@Override
	public void setOrderVo(OrderVo vo) {
		this.order = vo;
		
	}

	@Override
	public OrderVo getOrderVo() {
		return order;
	}
	
}
