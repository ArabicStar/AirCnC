package data.datahelper;

import java.util.Map;

import po.PromotionPo;

public interface PromotionDataHelper {
	/**
	 * @return	从数据文件中读取订单数据
	 */
	public Map<Integer,PromotionPo> getPromotionData();
	
	/**
	 * 向数据文件中写入订单数据
	 * @param list	
	 */
	public void updatePromotionData(Map<Integer,PromotionPo> map);
}
