package utils.info.order;

import utils.HotelDate;

public abstract class OrderInfoTemplate {
	protected String orderId;
	
	protected String roomType;
	
	protected int stayDays;
	
	protected int userId;
	
	protected OrderStatus status;
	
	protected HotelDate entryTime;
	
	/**
	 * 订单最晚执行时间
	 */
	protected HotelDate lastTime;

	/**
	 * 除去小孩的总人数
	 */
	protected int peopleNumber;
	
	protected boolean hasChildren;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getStayDays() {
		return stayDays;
	}

	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public HotelDate getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(HotelDate entryTime) {
		this.entryTime = entryTime;
	}

	public HotelDate getLastTime() {
		return lastTime;
	}

	public void setLastTime(HotelDate lastTime) {
		this.lastTime = lastTime;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
}
