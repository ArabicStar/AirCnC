package utils.info.order;

import java.time.LocalDateTime;

public abstract class OrderInfo extends OrderInfoTemplate {

	public String getOrderId() {
		return orderId;
	}

	public OrderInfo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getRoomType() {
		return roomType;
	}

	public OrderInfo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	public int getStayDays() {
		return stayDays;
	}

	public OrderInfo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	public int getUserId() {
		return userId;
	}

	public OrderInfo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public OrderInfo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public OrderInfo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public LocalDateTime getLastTime() {
		return lastTime;
	}

	public OrderInfo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public OrderInfo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public OrderInfo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}
	
	public double getPrice() {
		return price;
	}
	
	public OrderInfo setPrice(double price) {
		if(price < 0) {
			/**
			 * TODO:错误信息处理
			 */
			return null;
		}
		this.price = price;
		return this;
	}
	
}
