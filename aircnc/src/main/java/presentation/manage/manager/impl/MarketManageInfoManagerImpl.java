package presentation.manage.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.manage.manager.MarketManageInfoManager;
import presentation.manage.model.MarketManageModel;
import vo.market.MarketVo;

public class MarketManageInfoManagerImpl implements MarketManageInfoManager{
	
	private static MarketManageInfoManager instance;
	
	private MarketVo vo;
	private ObservableList<MarketManageModel> marketInfo = FXCollections.observableArrayList();
	
	
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
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}

	/**
	 * wrap into the HotelManageModel
	 */
	@Override
	public ObservableList<MarketManageModel> getMarketInfoList() {
		marketInfo.clear();
		marketInfo.add(new MarketManageModel(vo));
		return marketInfo;
	}

	@Override
	public MarketManageModel getMarketInfo() {
		if(vo == null)
			return null;
		return new MarketManageModel(vo);
	}
}
