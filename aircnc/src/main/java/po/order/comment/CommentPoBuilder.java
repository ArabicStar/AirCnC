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

	public CommentPoBuilder setContent(String content){
		if(checkCommentContent(content)){
			this.content = content;
		}
		return this;
	}
	
	public CommentPoBuilder setHotelID(int id) {
		if (checkID(id)) {
			this.hotelId = id;
		}
		return this;
	}
	
	public CommentPoBuilder setMemberID(String id) {
		if (checkID(id)) {
			this.memberId = id;
		}
		return this;
	}
	
	public CommentPoBuilder setCheckInTime(LocalDate checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	public CommentPoBuilder setCommentTime(LocalDateTime commentTime){
		this.commentTime = commentTime;
		return this;
	}
	
	public CommentPoBuilder setGrade(int grade){
		this.grade = grade;
		return this;
	}
	
	public CommentPoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	
	
	@Override
	public CommentPo getCommentInfo() {
		if (!isReady()) {
			return new CommentPo(0);
		}
		CommentPo po = new CommentPo(grade).setHotelId(hotelId).setMemberId(memberId).
		setCheckInTime(checkInTime).setCommentTime(commentTime).setContent(content).
		setOrderId(orderId).setGrade(grade);
		return po;
	}

}
