package presentation.market.accessor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import vo.order.OrderVo;

public interface MakeOrderAccessor {
	
	public void setRoomNumber(int roomNumber);
	
	public void setEnterTime(LocalDate enterTime);
	
	public void setLeaveTime(LocalDate leaveTime);
	
	public void setRoomType(String roomType);
	
	public void setLatestExecuteTime(LocalDateTime latestExecuteTime);
	
	public void setPeopleNumber(int peopleNumber);
	
	public void setHasChildren(boolean hasChildren);
	
	/**
	 * 获取已经生成的订单
	 * @return
	 */
	public OrderVo getMadeOrder();
}
