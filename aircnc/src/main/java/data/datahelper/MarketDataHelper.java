package data.datahelper;

import java.util.ArrayList;

import po.market.MarketPo;

public interface MarketDataHelper {

	/**
	 * @return	从数据文件中读取营销数据
	 */
	public ArrayList<MarketPo> getMarketData();

	/**
	 * 向数据文件中写入营销数据
	 * @param arraylist
	 */
	public void updateMarketData(ArrayList<MarketPo> marketData);
}
