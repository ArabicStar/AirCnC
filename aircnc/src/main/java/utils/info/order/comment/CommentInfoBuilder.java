package utils.info.order.comment;

import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.time.LocalDateTime;

import utils.info.hotel.HotelInfo;
import utils.info.order.OrderInfo;

public abstract class CommentInfoBuilder extends CommentInfoTemplate {

	protected CommentInfoBuilder() {
		this.id = 0;
		this.commentTime = LocalDateTime.now();
		this.content = "";
		this.grade = 0;
	}

	protected CommentInfoBuilder(CommentInfo info) {
		this();
		setId(info.getId()).setContent(info.getContent()).setCommentTime(info.getCommentTime())
				.setGrade(info.getGrade()).setHotel(info.getHotel());
	}

	protected CommentInfoBuilder(OrderInfo info) {
		setHotel(info.getHotel());
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public CommentInfoBuilder setId(long id) {
		if (id < 0)
			throw illegalArgEx("Comment id", id);
		this.id = id;
		return this;
	}

	/**
	 * @param commentTime
	 *            to be set commentTime
	 */
	public CommentInfoBuilder setCommentTime(LocalDateTime commentTime) {
		if (commentTime == null)
			throw illegalArgEx("Commemt time", commentTime);
		this.commentTime = commentTime;
		return this;
	}

	/**
	 * @param content
	 *            to be set content
	 */
	public CommentInfoBuilder setContent(String content) {
		if (content == null)
			throw illegalArgEx("Comment content", content);
		this.content = content;
		return this;
	}

	/**
	 * @param grade
	 *            to be set grade
	 */
	public CommentInfoBuilder setGrade(int grade) {
		if (grade < 0)
			throw illegalArgEx("Comment grade", grade);
		this.grade = grade;
		return this;
	}

	protected abstract HotelInfo getHotel();

	public boolean isReady() {
		return checkId(id) && getHotel() != null;
	}

	public abstract CommentInfoBuilder setHotel(HotelInfo hotel);

	public abstract CommentInfo getCommentInfo();
}
