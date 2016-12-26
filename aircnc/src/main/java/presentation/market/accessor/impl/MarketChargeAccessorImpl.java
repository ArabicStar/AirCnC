package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.market.accessor.MarketChargeAccessor;

public class MarketChargeAccessorImpl implements MarketChargeAccessor {
	private static MarketChargeAccessor instance;
	
	private String memberId;
	
	private int money;
	
	public static final MarketChargeAccessor launch() {
		if (instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new MarketChargeAccessorImpl();
	}
	
	public static final MarketChargeAccessor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}
	
	@Override
	public String getMemberId() {
		if (memberId == null) {
			throw accessorNotReadyEx();
		}
		return memberId;
	}

	@Override
	public int getTopupMoney() {
		if (money == 0) {
			throw accessorNotReadyEx();
		}
		return money;
	}

	@Override
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public void setTopupMoney(int money) {
		this.money = money;
	}

}
