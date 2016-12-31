package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.manager.MakeOrderManager;
import utils.info.order.OrderInfo;

public class MakeOrderManagerImpl implements MakeOrderManager{
	
private static MakeOrderManager instance;
	
	private OrderInfo info;
	
	public static final MakeOrderManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MakeOrderManagerImpl();
	}
	
	public static final MakeOrderManager getInstance(){
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
	public void setOrderVo(OrderInfo vo){
		this.info = vo;
	}
	
	@Override
	public OrderInfo getOrderVo(){
		return info;
	}
}
