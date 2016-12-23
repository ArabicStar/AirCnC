package utils.info.order.comment;

import java.time.LocalDateTime;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;

public abstract class CommentInfo extends CommentInfoTemplate {
	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return commentTime
	 */
	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return grade
	 */
	public int getGrade() {
		return grade;
	}

	public abstract OrderInfo getRelOrder();

	public abstract HotelInfo getHotel();

	public MemberInfo getMember() {
		final OrderInfo order = getRelOrder();
		return order == null ? null : order.getMember();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentInfo [id=").append(id).append(", commentTime=").append(commentTime).append(", grade=")
				.append(grade).append(", content=").append(content).append("]");
		return builder.toString();
	}

}
