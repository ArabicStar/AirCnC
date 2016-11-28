package po.order;

import java.time.LocalDateTime;

import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;

/**
 * FIXME:修正这个类的继承关系，重写属性和方法，构造方法也要改
 * FIXME:注意涉及构造方法的地方要在修改后看一下是否会报错
 * id 订单编号 
 * hotelId 酒店编号 
 * userId 用户编号 
 * status 订单状态（0：未执行订单；1：已执行订单；2：异常订单 ；3：撤销订单）
 * entryTime 用户入住时间 
 * lastTime 订单最晚执行时间 
 * orderInfo 订单详情 
 * price 订单价值
 */
public class OrderPo extends OrderInfo{



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

	@Override
	public OrderPo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderPo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderPo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderPo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public OrderPo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderPo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderPo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderPo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderPo setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public OrderPo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}
	
	@Override
	public OrderPo setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	@Override
	public OrderPo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderPo setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}
	

}
