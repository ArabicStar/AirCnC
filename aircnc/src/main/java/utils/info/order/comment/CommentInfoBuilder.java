package utils.info.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class CommentInfoBuilder extends CommentInfoTemplate{
	
	public CommentInfoBuilder() {
		// all these should be illegal
		hotelId = 0;
		memberId = "00000000";
		checkInTime = LocalDate.now();
		commentTime = LocalDateTime.now();
		content = "";
		grade = 0;
		orderId_c = "";
	}
	
	public CommentInfoBuilder(CommentInfo info){
		this(info.getGrade());
		this.setCheckInTime(info.getCheckInTime()).setCommentTime(info.getCommentTime()).setContent(info.getContent())
		.setHotelID(info.getHotelId()).setMemberID(info.getMemberId()).setOrderId_c(info.orderId_c);
	}
	
	public CommentInfoBuilder(int grade){
		if(grade == 0) {
			throw new IllegalArgumentException();
		}
		this.grade = grade;
	}
	
	public CommentInfoBuilder setContent(String content){
		if(checkCommentContent(content)){
			this.content = content;
		}
		return this;
	}
	
	public CommentInfoBuilder setHotelID(int id) {
		if (checkID(id)) {
			this.hotelId = id;
		}
		return this;
	}
	
	public CommentInfoBuilder setMemberID(String id) {
		if (checkID(id)) {
			this.memberId = id;
		}
		return this;
	}
	
	public CommentInfoBuilder setCheckInTime(LocalDate checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	public CommentInfoBuilder setCommentTime(LocalDateTime commentTime){
		this.commentTime = commentTime;
		return this;
	}
	
	public CommentInfoBuilder setOrderId_c(String orderId) {
		this.orderId_c = orderId;
		return this;
	}
	
	public boolean isReady() {
//		System.out.println(id + " " + name + " " + contact + " " + type + " " + enterprise + " " + birthday);
		return hotelId != 0 && memberId != null && content != null && checkInTime != null && commentTime != null;
	}
	
	public abstract CommentInfo getCommentInfo();
}
