package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;

import presentation.manage.accessor.MarketManageInfoAccessor;
import presentation.manage.model.MarketManageModel;
import vo.market.MarketVoBuilder;

public class MarketManageInfoAccessorImpl implements MarketManageInfoAccessor{
	
	private static MarketManageInfoAccessor instance;
	
	private String id;
	private String name;
	private int passwordHash;
	
	public static final MarketManageInfoAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketManageInfoAccessorImpl();
	}
	
	public static final MarketManageInfoAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public MarketVoBuilder getModifiedMarketVo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMarketId() {
		//else 报错
		if(id == null)
			throw accessorNotReadyEx();
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}

	@Override
	public void setMarketModel(MarketManageModel model) {
		
	}

}
