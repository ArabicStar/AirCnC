package vo.order;

import utils.info.order.OrderInfoBuilder;

public class OrderVoBuilder extends OrderInfoBuilder {

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setEntryTime(entryTime).setHasChildren(hasChildren)
				.setLastTime(lastTime).setOrderId(orderId).setPeopleNumber(peopleNumber)
				.setRoomType(roomType).setStatus(status).setStayDays(stayDays)
				.setUserId(userId);
	}
	
	 
}
