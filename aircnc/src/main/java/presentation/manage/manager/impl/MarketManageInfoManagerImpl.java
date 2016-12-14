package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.manage.manager.MarketManageInfoManager;
import presentation.manage.model.MarketManageModel;
import vo.market.MarketVo;

public class MarketManageInfoManagerImpl implements MarketManageInfoManager{
	
	private static MarketManageInfoManager instance;
	
	private MarketVo vo;
	private MarketManageModel marketInfo;
	
	public static final MarketManageInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketManageInfoManagerImpl();
	}
	
	public static final MarketManageInfoManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	@Override
	public boolean setMarket(MarketVo vo){
		if(vo!=null){
			this.vo = vo;
			return true;
		}
		return false;
	}

	/**
	 * wrap into the HotelManageModel
	 */
	@Override
	public MarketManageModel getMarketInfo() {
		marketInfo = new MarketManageModel(vo);
		return marketInfo;
	}
}
