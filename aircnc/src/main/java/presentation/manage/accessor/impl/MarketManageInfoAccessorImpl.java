package presentation.manage.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;

import presentation.manage.accessor.MarketManageInfoAccessor;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public class MarketManageInfoAccessorImpl implements MarketManageInfoAccessor{
	
	private static MarketManageInfoAccessor instance;
	
	private String id;
	private String name;
	private int passwordHash;
	private MarketVo vo;
	
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
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	@Override
	public MarketVo getModifiedMarketVo() {
		if(vo ==null)
			return null;
		MarketVo market = new MarketVoBuilder(vo).setName(name).getMarketInfo();
		return market;
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
	public void setMarketVo(MarketVo vo) {
		this.vo = vo;
	}
	
	@Override
	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}

	@Override
	public int getPasswordHash() {
		return passwordHash;
	}

	@Override
	public MarketVo getAddedMarketVo() {
		MarketVo market = new MarketVoBuilder().setName(name).getMarketInfo();
		return market;
	}

}
