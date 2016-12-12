package vo.order.comment;

import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;

public class CommentVo extends CommentInfo{

	protected CommentVo(int grade) {
		super(grade);
	}
	
	CommentVo setHotelId(String id){
		this.hotelId = id;
		return this;
	}
	
	CommentVo setMemberId(String id){
		this.memberId = id;
		return this;
	}
	
	CommentVo setContent(String content){
		this.content = content;
		return this;
	}
	
	CommentVo setCheckInTime(LocalDateTime checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	CommentVo setCommentTime(LocalDateTime commentTime){
		this.commentTime = commentTime;
		return this;
	}

}
