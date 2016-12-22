package po.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;

public class CommentPoBuilder extends CommentInfoBuilder{

	private static final CommentPo INVALID_COMMENT_PO;
	static {
		INVALID_COMMENT_PO = new CommentPo(1);
		INVALID_COMMENT_PO.invalidate();
	}

	public static final CommentPo getInvalidInfo() {
		return INVALID_COMMENT_PO;
	}
	
	public CommentPoBuilder() {
		super();
	}
	
	
	public CommentPoBuilder(CommentInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommentPoBuilder setContent(String content){
		super.setContent(content);
		return this;
	}
	
	@Override
	public CommentPoBuilder setHotelID(int id) {
		super.setHotelID(id);
		return this;
	}
	
	@Override
	public CommentPoBuilder setMemberID(String id) {
		super.setMemberID(id);
		return this;
	}
	
	@Override
	public CommentPoBuilder setCheckInTime(LocalDate checkInTime){
		super.setCheckInTime(checkInTime);
		return this;
	}
	
	@Override
	public CommentPoBuilder setCommentTime(LocalDateTime commentTime){
		super.setCommentTime(commentTime);
		return this;
	}
	
	public CommentPoBuilder setGrade(int grade){
		if(grade <= 0 || grade > 5) {
			return null;
		}
		this.grade = grade;
		return this;
	}
	
	@Override
	public CommentPoBuilder setOrderId_c(String orderId) {
		super.setOrderId_c(orderId);
		return this;
	}
	
	
	
	@Override
	public CommentPo getCommentInfo() {
		if (!isReady()) {
			return new CommentPo(0);
		}
		CommentPo po = new CommentPo(grade).setHotelId(hotelId).setMemberId(memberId).
		setCheckInTime(checkInTime).setCommentTime(commentTime).setContent(content).
		setOrderId_c(orderId_c).setGrade(grade);
		return po;
	}

}
