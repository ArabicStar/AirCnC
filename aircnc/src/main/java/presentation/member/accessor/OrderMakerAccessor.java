package presentation.member.accessor;

import java.time.LocalDateTime;

import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public interface OrderMakerAccessor {
	
	public void setRoomNumber(int roomNumber);
	
	public void setEnterTime(LocalDateTime entry);
	
	public void setLeaveTime(LocalDateTime leaveTime);
	
	public void setRoomType(String roomType);
	
	public void setLatestExecuteTime(LocalDateTime latestExecuteTime);
	
	public void setPeopleNumber(int peopleNumber);
	
	public void setHasChildren(boolean hasChildren);
	
	public void setHotel(HotelVo vo);
	
	/**
	 * 获取已经生成的订单
	 * @return
	 */
	public OrderVoBuilder getMadeOrder();
	
	public void setCompleteOrder(OrderVo vo);
	
	public OrderVo getCompleteOrder();
	
}
