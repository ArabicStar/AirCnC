package utils.info.comment;

public class CommentInfo extends CommentInfoTemplate{
	protected boolean isValid;

	private static final String BLANK = "";
	
	protected CommentInfo(int grade){
		hotelId = BLANK;
		memberId = BLANK;
		content = BLANK;
		this.grade = grade;
		checkInTime = BLANK;
		commentTime = BLANK;
	}
	
	public String getHotelId(){
		if (isValid())
			return hotelId;
		return null;
	}
	
	public String getMemberId(){
		if (isValid())
			return memberId;
		return null;
	}
	
	public String getContent(){
		if (isValid())
			return content;
		return null;
	}
	
	public int getGrade(){
		if (isValid())
			return grade;
		return Integer.MIN_VALUE;
	}
	
	public String getCheckInTime(){
		if (isValid())
			return checkInTime;
		return null;
	}
	
	public String getCommentTime(){
		if (isValid())
			return commentTime;
		return null;
	}	
	
	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}
}
