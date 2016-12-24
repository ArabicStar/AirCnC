package vo.order.comment;

import static utils.exception.StaticExceptionFactory.illegalStateException;

import java.time.LocalDateTime;

import utils.info.hotel.HotelInfo;
import utils.info.order.OrderInfo;
import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class CommentVoBuilder extends CommentInfoBuilder {
	private HotelVo hotel;

	public CommentVoBuilder() {
		super();
	}

	/**
	 * @param info
	 */
	public CommentVoBuilder(CommentInfo info) {
		super(info);
	}

	public CommentVoBuilder(OrderInfo info) {
		super(info);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	@Override
	public CommentInfoBuilder setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * @param commentTime
	 *            to be set commentTime
	 */
	@Override
	public CommentVoBuilder setCommentTime(LocalDateTime commentTime) {
		super.setCommentTime(commentTime);
		return this;
	}

	/**
	 * @param content
	 *            to be set content
	 */
	@Override
	public CommentVoBuilder setContent(String content) {
		super.setContent(content);
		return this;
	}

	/**
	 * @param grade
	 *            to be set grade
	 */
	@Override
	public CommentVoBuilder setGrade(int grade) {
		super.setGrade(grade);
		return this;
	}

	@Override
	public CommentVo getCommentInfo() {
		if (!isReady())
			throw illegalStateException("Comment Vo Builder not set up");

		return new CommentVo().setCommentTime(commentTime).setContent(content).setGrade(grade).setId(id)
				.setHotel(hotel);
	}

	@Override
	public CommentVoBuilder setHotel(HotelInfo hotel) {
		if (hotel.isValid())
			this.hotel = new HotelVoBuilder(hotel).getHotelInfo();
		return this;
	}

	@Override
	protected HotelInfo getHotel() {
		return hotel;
	}
}
