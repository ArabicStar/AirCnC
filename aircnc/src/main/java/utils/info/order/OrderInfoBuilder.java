package utils.info.order;

import java.time.LocalDateTime;

public abstract class OrderInfoBuilder extends OrderInfoTemplate {
	public OrderInfoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public OrderInfoBuilder setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	public OrderInfoBuilder setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	public OrderInfoBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public OrderInfoBuilder setStatus(OrderStatus orderStatus) {
		this.status = orderStatus;
		return this;
	}

	public OrderInfoBuilder setEntryTime(LocalDateTime entryTime) {
		/**
		 * TODO:处理异常情况
		 */
		return null;
	}

	public OrderInfoBuilder setLastTime(LocalDateTime lastTime) {
//		if (lastTime.isValid()) {
//			this.lastTime = lastTime;
//			return this;
//		}
		
		/**
		 * TODO:处理异常情况
		 */
		return null;
	}
	
	public OrderInfoBuilder setPeoPleNumber(int peopleNumber) {
		/**
		 * TODO:处理异常情况
		 */
		if(peopleNumber < 0) {
			return null;
		}
		this.peopleNumber = peopleNumber;
		return this;
	}
	
	public OrderInfoBuilder setHasChild(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	public abstract OrderInfo getOrderInfo();
}
