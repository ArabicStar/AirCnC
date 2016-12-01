package vo.order;

import java.time.LocalDateTime;

import po.member.MemberPo;
import po.order.OrderPo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;

/**
 * orderNo 订单编号 
 * orderInfo 订单详情 
 * userInfo 用户信息（用户名+信用值信息） 
 * entryTime 用户入住时间
 * lastTime 订单最晚执行时间 
 * orderStatus 订单状态
 * 
 */
public class OrderVo extends OrderInfo {
	
	/**
	 * TODO:无参构造方法，目前还不知道需要不需要在这个里面初始化参数
	 */
	public OrderVo() {
		
	}
	
	public OrderVo(OrderPo orderPo, MemberPo memberPo) {
		
	}

	@Override
	public OrderVo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderVo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderVo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderVo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public OrderVo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderVo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderVo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderVo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderVo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}
	
	@Override
	public OrderVo setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public OrderVo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}
	
	@Override
	public OrderVo setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	@Override
	public OrderVo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderVo setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	@Override
	public OrderVo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

}
