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
	
	protected double price;

	protected boolean hasChildren;

	public HotelDate getEntryTime() {
		return entryTime;
	}
	
	public HotelDate getLastTime() {
		return lastTime;
	}
}
