package po.order;

import static utils.exception.StaticExceptionFactory.illegalStateException;
import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import po.order.comment.CommentPo;
import po.order.comment.CommentPoBuilder;
import po.promotion.PromotionPo;
import po.promotion.PromotionPoBuilder;
import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.info.order.comment.CommentInfo;
import utils.info.promotion.PromotionInfo;

public class OrderPoBuilder extends OrderInfoBuilder {
	private Set<PromotionPo> promotions;
	private CommentPo comment;
	private MemberPo member;
	private HotelPo hotel;

	public OrderPoBuilder() {
		super();
		promotions = new HashSet<>();
	}

	public OrderPoBuilder(OrderInfo info) {
		super(info);
		promotions = new HashSet<>();
		setPromotions(info.getPromotions());
	}

	@Override
	public OrderPo getOrderInfo() {
		if (!isReady())
			throw illegalStateException("Order Po Builder not set up");

		return new OrderPo().setOrderId(orderId)//
				.setStatus(status)//
				.setMember(member)//
				.setHotel(hotel)//
				.setRoomType(roomType)//
				.setRoomNumber(roomNumber)//
				.setEntryTime(entryTime)//
				.setLastTime(lastTime)//
				.setStayDays(stayDays)//
				.setPeopleNumber(peopleNumber)//
				.setHasChildren(hasChildren)//
				.setOriginalPrice(originalPrice)//
				.setDiscountPrice(discountPrice)//
				.setPromotions(promotions)//
				.setComment(comment)//
				.setAppeal(appeal);//
	}

	@Override
	public OrderPoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderPoBuilder setStatus(OrderStatus status) {

		this.status = status;
		return this;
	}

	@Override
	public OrderPoBuilder setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderPoBuilder setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderPoBuilder setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}

	@Override
	public OrderPoBuilder setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	@Override
	public OrderPoBuilder setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderPoBuilder setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderPoBuilder setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderPoBuilder setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderPoBuilder setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	@Override
	public OrderPoBuilder setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	@Override
	public OrderInfoBuilder addPromotion(PromotionInfo promotion) {
		promotions.add(new PromotionPoBuilder(promotion).getPromotionInfo());
		return this;
	}

	@Override
	public OrderPoBuilder setPromotions(Set<? extends PromotionInfo> promotions) {
		this.promotions.addAll(promotions.stream().map(info -> new PromotionPoBuilder(info).getPromotionInfo())
				.collect(Collectors.toSet()));
		return this;
	}

	@Override
	public OrderPoBuilder setComment(CommentInfo comment) {
		this.comment = new CommentPoBuilder(comment).getCommentInfo();
		return this;
	}

	@Override
	public OrderPoBuilder setHotel(HotelInfo info) {
		this.hotel = new HotelPoBuilder(info).getHotelInfo();
		return this;
	}

	@Override
	public OrderPoBuilder setMember(MemberInfo info) {
		this.member = new MemberPoBuilder(info).getMemberInfo();
		return this;
	}

	/**
	 * 
	 * @param from
	 *            the po you want
	 * @param to
	 *            the po you have
	 */
	public static void updatePo(OrderPo from, OrderPo to) {
		if (from == null || to == null || from == to)
			return;

		if (!from.getOrderId().equals(to.getOrderId()))
			throw inconsistentStatusEx();

		if (!from.isReviewed() && to.isReviewed())
			throw unsupportedOpEx("Couldn't revert the comment");

		to.setStatus(from.getStatus());
		to.setComment(from.getComment());
	}

	@Override
	protected MemberInfo getMember() {
		return member;
	}

	@Override
	protected HotelInfo getHotel() {
		return hotel;
	}
}
