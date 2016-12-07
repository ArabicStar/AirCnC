package presentation.manage.manager.impl;

import presentation.manage.manager.MarketManageInfoManager;
import presentation.manage.model.MarketManageModel;
import vo.market.MarketVo;

public class MarketManageInfoManagerImpl implements MarketManageInfoManager{
	
	private MarketVo vo;
	private MarketManageModel marketInfo;
	
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
