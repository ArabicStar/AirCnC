package service;

import java.util.List;

import po.hotel.RoomPo;
import vo.CommentVo;
import vo.HotelVo;
import vo.hotel.RoomVo;
import vo.order.OrderVo;

public interface HotelService {
	
	/**
	 * 
	 * @param hotelId
	 * @return  获取酒店的最低房价
	 */
	public double getHotelLowestPrice(String hotelId);
	
	/**
	 * 查看自己在该酒店的以往订单
	 * @param userId
	 * @param hotelId
	 * @return  
	 */
	public List<OrderVo> checkMyOrder(String userId,String hotelId);
	
	
	/**
	 * 获取酒店详细信息
	 * @param hotelId
	 * @return
	 */
	public HotelVo getHotelDetailInfo(String hotelId);
	
	
	/**
	 * 获取酒店的简略信息，呈现在列表中
	 * @param hotelId
	 * @return
	 */
	public String getHotelSimpleInfo(String hotelId);
	
	
	/**
	 * 获取该酒店的评论
	 * @param hotelId
	 * @return
	 */
	public List<CommentVo> getHotelComments(String hotelId);
	
	
	public boolean addRoom(RoomPo room);
	
	public List<RoomVo> getRooms();
	
	
	
	
}
