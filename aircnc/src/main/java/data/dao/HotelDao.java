package data.dao;

import java.util.List;

import po.HotelPo;

public interface HotelDao {
	/**
	 * @param hotelId
	 * @return	获取酒店信息
	 */
	public HotelPo getHotel(int hotelId);
	
	/**
	 * @param scope  输入商圈
	 * @return	获取酒店列表
	 */
	public List<HotelPo> getHotels(String scope);
	
	/**
	 * @param hotelPo
	 * @return	更新酒店信息
	 */
	public boolean updateHotel(HotelPo hotelPo);
	
	/**
	 * @param hotelPo
	 * @return	添加酒店
	 */
	public boolean addHotelPo(HotelPo hotelPo);
	
	/**
	 * @param hotelId
	 * @return	删除酒店
	 */
	public boolean deleteHotelPo(int hotelId);
}
