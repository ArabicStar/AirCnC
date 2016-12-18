package vo.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;

public class CommentVoBuilder extends CommentInfoBuilder {

	private static final CommentVo INVALID_COMMENT_VO;
	static {
		INVALID_COMMENT_VO = new CommentVo(1);
		INVALID_COMMENT_VO.invalidate();
	}

	public static final CommentVo getInvalidInfo() {
		return INVALID_COMMENT_VO;
	}

	public CommentVoBuilder(CommentInfo info) {
		super(info);
	}

	public CommentVoBuilder() {
		super();
		System.out.println("调用");
	}

	public CommentVoBuilder setContent(String content) {
		if (checkCommentContent(content)) {
			this.content = content;
		}
		return this;
	}

	public CommentVoBuilder setHotelID(String id) {
		if (checkID(id)) {
			this.hotelId = id;
		}
		return this;
	}

	public CommentVoBuilder setMemberID(String id) {
		if (checkID(id)) {
			this.memberId = id;
		}
		return this;
	}

	public CommentVoBuilder setCheckInTime(LocalDate checkInTime) {
		this.checkInTime = checkInTime;
		return this;
	}

	public CommentVoBuilder setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
		return this;
	}

	@Override
	public CommentVo getCommentInfo() {
		if (!isReady()) {
			return null;
		} 
		return new CommentVo(grade).setHotelId(hotelId).setMemberId(memberId).setCheckInTime(checkInTime)
				.setCommentTime(commentTime);

	}

}
