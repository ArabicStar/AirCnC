package vo.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;

public class CommentVoBuilder extends CommentInfoBuilder {
	private int memberLevel;
	private String memberName;
	
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
	}

	@Override
	public CommentVoBuilder setContent(String content) {
		super.setContent(content);
		return this;
	}

	@Override
	public CommentVoBuilder setHotelID(int id) {
		super.setHotelID(id);
		return this;
	}

	@Override
	public CommentVoBuilder setMemberID(String id) {
		super.setMemberID(id);
		return this;
	}

	@Override
	public CommentVoBuilder setCheckInTime(LocalDate checkInTime) {
		super.setCheckInTime(checkInTime);
		return this;
	}

	@Override
	public CommentVoBuilder setCommentTime(LocalDateTime commentTime) {
		super.setCommentTime(commentTime);
		return this;
	}
	
	public CommentVoBuilder setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}
	
	public CommentVoBuilder setMemberName(String memberName) {
		if(memberName == null) {
			return null;
		}
		this.memberName = memberName;
		return this;
	}
	
	public CommentVoBuilder setGrade(int grade) {
		if(grade <= 0 || grade > 5) {
			return null;
		}
		this.grade = grade;
		return this;
	}

	@Override
	public CommentVoBuilder setOrderId_c(String orderId) {
		super.setOrderId_c(orderId);
		return this;
	}
	@Override
	public CommentVo getCommentInfo() {
		if (!isReady()) {
			return null;
		} 
		return new CommentVo(grade).setHotelId(hotelId).setMemberId(memberId).setCheckInTime(checkInTime)
				.setContent(content).setCommentTime(commentTime).setMemberLevel(memberLevel)
				.setMemberName(memberName).setOrderId_c(orderId_c).setGrade(grade);

	}

}
