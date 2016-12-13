package po.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;

public class CommentPo extends CommentInfo{

	protected CommentPo(int grade) {
		super(grade);
		// TODO Auto-generated constructor stub
	}
	
	CommentPo setHotelId(String id){
		this.hotelId = id;
		return this;
	}
	
	CommentPo setMemberId(String id){
		this.memberId = id;
		return this;
	}
	
	CommentPo setContent(String content){
		this.content = content;
		return this;
	}
	
	CommentPo setCheckInTime(LocalDate checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	CommentPo setCommentTime(LocalDateTime commentTime){
		this.commentTime = commentTime;
		return this;
	}

}
