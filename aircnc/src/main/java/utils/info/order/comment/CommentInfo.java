package utils.info.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentInfo extends CommentInfoTemplate{
	protected boolean isValid;

	private static final String BLANK = "";
	
	protected CommentInfo(int grade){
		hotelId = BLANK;
		memberId = BLANK;
		content = BLANK;
		this.grade = grade;
		checkInTime = null;
		commentTime = null;
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
	
	public LocalDate getCheckInTime(){
		if (isValid())
			return checkInTime;
		return null;
	}
	
	public LocalDateTime getCommentTime(){
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
