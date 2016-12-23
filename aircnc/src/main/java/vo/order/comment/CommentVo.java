package vo.order.comment;

import java.time.LocalDateTime;

import utils.info.hotel.HotelInfo;
import utils.info.order.comment.CommentInfo;
import vo.hotel.HotelVo;
import vo.order.OrderVo;

public class CommentVo extends CommentInfo {

	private OrderVo order;
	private HotelVo hotel;

	/**
	 * @param id
	 *            to be set id
	 */
	public CommentVo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param commentTime
	 *            to be set commentTime
	 */
	public CommentVo setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
		return this;
	}

	/**
	 * @param content
	 *            to be set content
	 */
	public CommentVo setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * @param grade
	 *            to be set grade
	 */
	public CommentVo setGrade(int grade) {
		this.grade = grade;
		return this;
	}

	@Override
	public OrderVo getRelOrder() {
		return order;
	}

	public CommentVo setRelOrder(OrderVo order) {
		this.order = order;
		return this;
	}

	@Override
	public HotelInfo getHotel() {
		return hotel;
	}

	public CommentVo setHotel(HotelVo hotel) {
		this.hotel = hotel;
		return this;
	}
}
