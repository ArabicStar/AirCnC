package vo.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.info.order.comment.CommentInfo;
import utils.info.promotion.PromotionInfo;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class OrderVoBuilder extends OrderInfoBuilder {
	private static final OrderVo INVALID_ORDER_VO;
	static {
		INVALID_ORDER_VO = new OrderVo();
		INVALID_ORDER_VO.invalidate();
	}

	public static final OrderInfo invalidOrderInfo() {
		return INVALID_ORDER_VO;
	}

	private Set<PromotionVo> promotions;
	private CommentVo comment;
	private MemberVo member;
	private HotelVo hotel;

	public OrderVoBuilder() {
		super();
		promotions = new HashSet<>();
	}

	public OrderVoBuilder(OrderInfo info) {
		super(info);
		promotions = new HashSet<>();
	}

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setOrderId(orderId)//
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
	public OrderVoBuilder setOrderId(String orderId) {
		super.setOrderId(orderId);
		return this;
	}

	@Override
	public OrderVoBuilder setRoomType(String roomType) {
		super.setRoomType(roomType);
		return this;
	}

	@Override
	public OrderVoBuilder setStayDays(int stayDays) {
		super.setStayDays(stayDays);
		return this;
	}

	@Override
	public OrderVoBuilder setStatus(OrderStatus status) {
		super.setStatus(status);
		return this;
	}

	@Override
	public OrderVoBuilder setEntryTime(LocalDateTime entryTime) {
		super.setEntryTime(entryTime);
		return this;
	}

	@Override
	public OrderVoBuilder setLastTime(LocalDateTime lastTime) {
		super.setLastTime(lastTime);
		return this;
	}

	@Override
	public OrderVoBuilder setPeopleNumber(int peopleNumber) {
		super.setPeopleNumber(peopleNumber);
		return this;
	}

	@Override
	public OrderVoBuilder setOriginalPrice(double price) {
		super.setOriginalPrice(price);
		return this;
	}

	@Override
	public OrderVoBuilder setHasChildren(boolean hasChildren) {
		super.setHasChildren(hasChildren);
		return this;
	}

	@Override
	public OrderVoBuilder setRoomNumber(int roomNumber) {
		super.setRoomNumber(roomNumber);
		return this;
	}

	@Override
	public OrderVoBuilder setDiscountPrice(double discountPrice) {
		super.setDiscountPrice(discountPrice);
		return this;
	}

	public OrderVoBuilder setComment(CommentVo comment) {
		this.comment = comment;
		return this;
	}

	@Override
	public OrderVoBuilder setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	@Override
	public OrderVoBuilder addPromotion(PromotionInfo promotion) {
		this.promotions.add(new PromotionVoBuilder(promotion).getPromotionInfo());
		return this;
	}

	@Override
	public OrderVoBuilder setPromotions(Set<? extends PromotionInfo> promotions) {
		this.promotions.addAll(promotions.stream().map(info -> new PromotionVoBuilder(info).getPromotionInfo())
				.collect(Collectors.toSet()));
		return this;
	}

	@Override
	public OrderInfoBuilder setComment(CommentInfo comment) {
		this.comment = new CommentVoBuilder(comment).getCommentInfo();
		return this;
	}

	@Override
	public OrderInfoBuilder setHotel(HotelInfo info) {
		this.hotel = new HotelVoBuilder(info).getHotelInfo();
		return this;
	}

	@Override
	public OrderInfoBuilder setMember(MemberInfo info) {
		this.member = new MemberVoBuilder(info).getMemberInfo();
		return this;
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
