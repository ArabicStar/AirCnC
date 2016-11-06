package data.datahelper;

import java.util.Map;

import po.HotelPo;


public interface HotelDataHelper {
	
	/**
	 * @return	从数据文件中读取酒店数据
	 */
	public Map<String,HotelPo> getHotelData();
	
	/**
	 * 向数据文件中写入酒店数据
	 * @param list	
	 */
	public void updateHotelData(Map<String,HotelPo> map);
}
