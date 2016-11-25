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
	 * TODO:添加具体代码
	 * @return
	 */
	public static boolean checkOrderId() {
		return true;
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


}
