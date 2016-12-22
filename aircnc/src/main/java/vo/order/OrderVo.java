package vo.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.member.MemberVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

/**
 * orderNo 订单编号 orderInfo 订单详情 userInfo 用户信息（用户名+信用值信息） entryTime 用户入住时间
 * lastTime 订单最晚执行时间 orderStatus 订单状态
 * 
 */
public class OrderVo extends OrderInfo {

	private Set<PromotionVo> promotions;
	private CommentVo comment;
	private MemberVo member;
	private HotelVo hotel;

	public OrderVo() {
		promotions = new HashSet<>();
	}

	OrderVo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	OrderVo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	OrderVo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	OrderVo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	OrderVo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	OrderVo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	OrderVo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	OrderVo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	OrderVo setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}

	OrderVo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	OrderVo setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	OrderVo setComment(CommentVo comment) {
		this.comment = comment;
		comment.setOrder(this);
		return this;
	}

	OrderVo setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	@Override
	public Set<PromotionVo> getPromotions() {
		return new HashSet<>(promotions);
	}

	OrderVo setPromotions(Set<PromotionVo> promotions) {
		this.promotions = new HashSet<>(promotions);
		return this;
	}

	@Override
	public CommentVo getComment() {
		return comment;
	}

	OrderVo setMember(MemberVo member) {
		this.member = member;
		return this;
	}

	@Override
	public MemberInfo getMember() {
		return member;
	}

	OrderVo setHotel(HotelVo hotel) {
		this.hotel = hotel;
		return this;
	}

	@Override
	public HotelInfo getHotel() {
		return hotel;
	}

}
