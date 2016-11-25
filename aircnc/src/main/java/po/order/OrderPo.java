package po.order;

import java.time.LocalDateTime;

import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;

public class OrderPo extends OrderInfo{
	private int hotelId;
	
	public OrderPo() {
		super();
	}
	
	
	public int getHotelId() {
		return hotelId;
	}
	
	public OrderPo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}
	
	public OrderPo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public OrderPo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}
	
	public OrderPo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}
	
	public OrderPo setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	
	public OrderPo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}
	
	public OrderPo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}
	
	public OrderPo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}
	
	public OrderPo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}
	
	public OrderPo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}
	
	public OrderPo setPrice(double price) {
		this.price = price;
		return this;
	}
}
