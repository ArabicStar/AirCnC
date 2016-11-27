package utils.info.order;

import java.time.LocalDateTime;

public abstract class OrderInfoTemplate {
	protected String orderId;

	protected String roomType;

	protected int stayDays;

	protected int userId;

	protected OrderStatus status;

	protected LocalDateTime entryTime;

	/**
	 * 订单最晚执行时间
	 */
	protected LocalDateTime lastTime;

	/**
	 * 除去小孩的总人数
	 */
	protected int peopleNumber;
	
	protected double price;

	protected boolean hasChildren;
	
	/**
	 * TODO:添加其他测试代码
	 * @return
	 */
	public static boolean checkOrderId(String orderId) {
		if(orderId.length() % 2 == 1) {
			return false;
		}
		if(orderId.length() < 16) {
			return false;
		}
		if(!isNumber(orderId)){
			return false;
		}
		return true;
	}
	
	private static boolean isNumber(String str) {
		if(str.matches("[0-9]+")) {
			return true;
		}
		return false;
	}


	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	
	public LocalDateTime getLastTime() {
		return lastTime;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getStayDays() {
		return stayDays;
	}

	public int getUserId() {
		return userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public double getPrice() {
		return price;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public abstract OrderInfoTemplate setOrderId(String orderId);

	public abstract OrderInfoTemplate setRoomType(String roomType);

	public abstract OrderInfoTemplate setStayDays(int stayDays);

	public abstract OrderInfoTemplate setUserId(int userId);

	public abstract OrderInfoTemplate setStatus(OrderStatus status);

	public abstract OrderInfoTemplate setEntryTime(LocalDateTime entryTime);

	public abstract OrderInfoTemplate setLastTime(LocalDateTime lastTime);

	public abstract OrderInfoTemplate setPeopleNumber(int peopleNumber);

	public abstract OrderInfoTemplate setPrice(double price);

	public abstract OrderInfoTemplate setHasChildren(boolean hasChildren);

}
