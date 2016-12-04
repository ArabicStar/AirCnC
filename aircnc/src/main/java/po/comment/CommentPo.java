package po.comment;

import utils.info.comment.CommentInfo;

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
	
	CommentPo setCheckInTime(String checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	CommentPo setCommentTime(String commentTime){
		this.commentTime = commentTime;
		return this;
	}

}
