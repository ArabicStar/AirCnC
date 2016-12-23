package po.order.comment;

import java.time.LocalDateTime;

import po.hotel.HotelPo;
import po.order.OrderPo;
import utils.info.order.OrderInfo;
import utils.info.order.comment.CommentInfo;

public class CommentPo extends CommentInfo {

	private OrderPo relOrder;
	private HotelPo hotel;

	public CommentPo() {
		super();
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public CommentPo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param commentTime
	 *            to be set commentTime
	 */
	public CommentPo setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
		return this;
	}

	/**
	 * @param content
	 *            to be set content
	 */
	public CommentPo setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * @param grade
	 *            to be set grade
	 */
	public CommentPo setGrade(int grade) {
		this.grade = grade;
		return this;
	}

	@Override
	public OrderInfo getRelOrder() {
		return relOrder;
	}

	public CommentPo setRelOrder(OrderPo order) {
		this.relOrder = order;
		return this;
	}

	@Override
	public HotelPo getHotel() {
		return hotel;
	}

	public CommentPo setHotel(HotelPo hotel) {
		this.hotel = hotel;
		return this;
	}
}
