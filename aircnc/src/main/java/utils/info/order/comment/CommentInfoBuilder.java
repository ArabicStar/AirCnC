package utils.info.order.comment;


public abstract class CommentInfoBuilder extends CommentInfoTemplate{
	public CommentInfoBuilder(CommentInfo info){
		this(info.getGrade());
		this.setCheckInTime(info.getCheckInTime()).setCommentTime(info.getCommentTime()).setContent(info.getContent())
		.setHotelID(info.getHotelId()).setMemberID(info.getMemberId());
	}
	
	public CommentInfoBuilder(int grade){
		if(grade == 0)
			throw new IllegalArgumentException();
		
		this.grade = grade;
	}
	
	public CommentInfoBuilder setContent(String content){
		if(checkCommentContent(content)){
			this.content = content;
		}
		return this;
	}
	
	public CommentInfoBuilder setHotelID(String id) {
		if (checkID(id))
			this.hotelId = id;
		return this;
	}
	
	public CommentInfoBuilder setMemberID(String id) {
		if (checkID(id))
			this.memberId = id;
		return this;
	}
	
	public CommentInfoBuilder setCheckInTime(String checkInTime){
		this.checkInTime = checkInTime;
		return this;
	}
	
	public CommentInfoBuilder setCommentTime(String commentTime){
		this.commentTime = commentTime;
		return this;
	}
	
	public boolean isReady() {
//		System.out.println(id + " " + name + " " + contact + " " + type + " " + enterprise + " " + birthday);
		return hotelId != null && memberId != null && content != null && checkInTime != null && commentTime != null;
	}
	
	public abstract CommentInfo getCommentInfo();
}
