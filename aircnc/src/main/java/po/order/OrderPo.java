package po.order;

import java.time.LocalDateTime;

import utils.HotelDate;
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

	private String id;

	private int hotelId;

	private int userId;

	private OrderStatus status;

	private LocalDateTime entryTime;

	private LocalDateTime lastTime;

	private String orderInfo;

	private int price;

	/**
	 * 房间数量
	 */
	private int roomNumber;

	/**
	 * 小孩人数
	 */
	private int childrenNumber;

	/**
	 * 总人数（包括小孩）
	 */
	private int peopleNumber;

	public OrderPo() {
		super();
	}

	public OrderPo(String id, int hotelId, int userId, OrderStatus status, 
			LocalDateTime entryTime, LocalDateTime lastTime, String orderInfo,
			int price, int roomNumber, int peopleNumber, int childrenNumber) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.userId = userId;
		this.status = status;
		this.entryTime = entryTime;
		this.lastTime = lastTime;
		this.orderInfo = orderInfo;
		this.price = price;
		this.roomNumber = roomNumber;
		this.peopleNumber = peopleNumber;
		this.childrenNumber = childrenNumber;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getUserId() {
		return userId;
	}

	public OrderPo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public OrderPo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public OrderPo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public LocalDateTime getLastTime() {
		return lastTime;
	}

	public OrderPo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public OrderPo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

}
