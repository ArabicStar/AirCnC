package vo.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;

public class CommentVo extends CommentInfo{
	private int memberLevel;
	private String memberName;
	
	protected CommentVo() {
		super();
	}

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
	
	CommentVo setCheckInTime(LocalDate checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	CommentVo setCommentTime(LocalDateTime commentTime){
		this.commentTime = commentTime;
		return this;
	}
	
	public CommentVo setMemberName(String memberName) {
		this.memberName = memberName;
		return this;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public CommentVo setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}
	
	public int getMemberLevel() {
		return memberLevel;
	}

}
