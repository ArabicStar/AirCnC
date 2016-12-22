package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.market.accessor.MarketLoginAccessor;

public class MarketLoginAccessorImpl implements MarketLoginAccessor {

	private static MarketLoginAccessor instance;

	private String id;

	private int passwordHash;

	public static final MarketLoginAccessor launch() {
		if (instance == null) {
			throw duplicateSingletonEx();
		}
		return instance = new MarketLoginAccessorImpl();
	}

	public static final MarketLoginAccessor getInstance() {
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
	public String getId() {
		if (id == null) {
			throw accessorNotReadyEx();
		}
		return id;
	}

	@Override
	public int getPasswordHash() {
		if (passwordHash == 0) {
			throw accessorNotReadyEx();
		}
		return passwordHash;
	}

	@Override
	public void setDeliveredId(String id) {
		this.id = id;
	}

	@Override
	public void setDeliveredPassword(String password) {
		this.passwordHash = password.hashCode();
	}

}
