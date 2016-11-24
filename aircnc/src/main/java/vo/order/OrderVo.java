package vo.order;

import java.time.LocalDateTime;

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
//	public OrderVo(OrderPo orderPo, MemberPo memberPo) {
//		String userInfo = memberPo.getName() + "(" + memberPo.getCredit() + ")";
//		this.add(String.valueOf(orderPo.getId()));
//		this.add(orderPo.getOrderInfo());
//		this.add(userInfo);
//		String entryTime = orderPo.getEntryTime();
//		this.add((entryTime.equals("")) ? "尚未入住" : entryTime);
//		this.add(orderPo.getLastTime());
//		switch (orderPo.getStatus()) {
//		case 0:// 订单未执行
//			this.add("订单未执行");
//			break;
//		case 1:// 订单已执行
//			this.add("订单已执行");
//			break;
//		case 2:// 订单异常
//			this.add("订单异常");
//			break;
//		default:
//			this.add("异常订单状态");
//			break;
//		}
//		this.add(String.valueOf(orderPo.getPrice()));
//	}
//
//	public OrderVo(int orderNo, String orderInfo, String userInfo, String entryTime, String lastTime,
//			String orderStatus, int price) {
//		this.add(String.valueOf(orderNo));
//		this.add(orderInfo);
//		this.add(userInfo);
//		this.add(entryTime);
//		this.add(lastTime);
//		this.add(orderStatus);
//		this.add(String.valueOf(price));
//	}
//
//	public int getOrderNo() {
//		return Integer.valueOf(this.get(0));
//	}
//
//	public String getOrderInfo() {
//		return this.get(1);
//	}
//
//	public String getUserInfo() {
//		return this.get(2);
//	}
//
//	public String getEntryTime() {
//		return this.get(3);
//	}
//
//	public String getLastTime() {
//		return this.get(4);
//	}
//
//	public String getOrderStatus() {
//		return this.get(5);
//	}
//
//	public int getPrice() {
//		return Integer.valueOf(this.get(6));
//	}
//
//	public String getHotel() {
//		return "";
//	}

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
