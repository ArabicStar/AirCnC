package po;

/**
 * id 订单编号 
 * hotelId 
 * 酒店编号 userId 
 * 用户编号 status 
 * 订单状态（0：未执行订单；1：已执行订单；2：异常订单 ；3：撤销订单）
 * entryTime 用户入住时间 
 * lastTime 订单最晚执行时间 
 * orderInfo 订单详情 
 * price 订单价值
 */
public class OrderPo {

	private int id;

	private int hotelId;

	private int userId;

	private int status;

	private String entryTime;

	private String lastTime;

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

	public OrderPo(int id, int hotelId, int userId, int status, 
			String entryTime, String lastTime, String orderInfo,
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public int getPrice() {
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

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

}
