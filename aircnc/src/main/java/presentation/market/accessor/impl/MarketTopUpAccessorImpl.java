package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.market.accessor.MarketTopUpAccessor;

public class MarketTopUpAccessorImpl implements MarketTopUpAccessor {
	private static MarketTopUpAccessor instance;
	
	private String memberId;
	
	private int money;
	
	public static final MarketTopUpAccessor launch() {
		if (instance == null) {
			throw duplicateSingletonEx();
		}
		return instance = new MarketTopUpAccessorImpl();
	}
	
	public static final MarketTopUpAccessor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}
	
	public static boolean isLaunched() {
		if (instance == null) {
			return false;
		} else {
			return true;
		}
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
