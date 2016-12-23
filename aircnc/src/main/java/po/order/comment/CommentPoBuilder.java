package po.order.comment;

import static utils.exception.StaticExceptionFactory.illegalStateException;

import java.time.LocalDateTime;

import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import utils.info.hotel.HotelInfo;
import utils.info.order.OrderInfo;
import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;

public class CommentPoBuilder extends CommentInfoBuilder {

	private HotelPo hotel;

	public CommentPoBuilder() {
		super();
	}

	/**
	 * @param info
	 */
	public CommentPoBuilder(CommentInfo info) {
		super(info);
	}

	public CommentPoBuilder(OrderInfo info){
		super(info);
	}
	/**
	 * @param id
	 *            to be set id
	 */
	@Override
	public CommentPoBuilder setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * @param commentTime
	 *            to be set commentTime
	 */
	@Override
	public CommentPoBuilder setCommentTime(LocalDateTime commentTime) {
		super.setCommentTime(commentTime);
		return this;
	}

	/**
	 * @param content
	 *            to be set content
	 */
	@Override
	public CommentPoBuilder setContent(String content) {
		super.setContent(content);
		return this;
	}

	/**
	 * @param grade
	 *            to be set grade
	 */
	@Override
	public CommentPoBuilder setGrade(int grade) {
		super.setGrade(grade);
		return this;
	}

	@Override
	public CommentPoBuilder setHotel(HotelInfo hotel) {
		this.hotel = new HotelPoBuilder(hotel).getHotelInfo();
		return this;
	}

	@Override
	public boolean isReady() {
		return super.isReady();
	}

	@Override
	public CommentPo getCommentInfo() {
		if (!isReady())
			throw illegalStateException("Comment Po Builder not set up");

		return new CommentPo().setCommentTime(commentTime).setContent(content).setGrade(grade).setId(id)
				.setHotel(hotel);
	}

	@Override
	protected HotelInfo getHotel() {
		return hotel;
	}

}
