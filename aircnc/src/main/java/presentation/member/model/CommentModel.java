package presentation.member.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.order.comment.CommentVo;

public class CommentModel {
	private final StringProperty memberName;
	private final StringProperty level;
	private final StringProperty checkInTime;
	private final StringProperty grade;
	private final StringProperty content;
	private final StringProperty commentTime;
	
	public CommentModel(){
		this(null);
	}
	
	public CommentModel(CommentVo vo){
		this.memberName = new SimpleStringProperty(vo.getMemberName());
		this.level = new SimpleStringProperty("Lv"+Integer.toString(vo.getMemberLevel()));
		this.checkInTime = new SimpleStringProperty("于"+vo.getCheckInTime().toString()+"入住");
		this.commentTime = new SimpleStringProperty(transformTime(vo.getCommentTime()));
		this.grade = new SimpleStringProperty(Integer.toString(vo.getGrade()));
		this.content = new SimpleStringProperty("    "+vo.getContent());
	}
	
	public String getGrade() {
        return grade.get();
    }

    public void setGrade(String newGrade) {
        this.grade.set(newGrade);
    }

    public StringProperty gradeProperty() {
        return grade;
    }
    
    public String getMemberName() {
        return memberName.get();
    }

    public void setMemberName(String newMemberName) {
        this.memberName.set(newMemberName);
    }

    public StringProperty memberNameProperty() {
        return memberName;
    }
    
    public String getCheckInTime() {
        return checkInTime.get();
    }

    public void setCheckInTime(String newCheckInTime) {
        this.checkInTime.set(newCheckInTime);
    }

    public StringProperty checkInTimeProperty() {
        return checkInTime;
    }
    
    public String getCommentTime() {
        return commentTime.get();
    }

    public void setCommentTime(String newCommentTime) {
        this.commentTime.set(newCommentTime);
    }

    public StringProperty commentTimeProperty() {
        return commentTime;
    }
    
    public String getContent() {
        return content.get();
    }

    public void setContent(String newContent) {
        this.content.set(newContent);
    }

    public StringProperty contentProperty() {
        return content;
    }
    
    public String getLevel() {
        return level.get();
    }

    public void setLevel(String newLevel) {
        this.level.set(newLevel);
    }

    public StringProperty levelProperty() {
        return level;
    }
    
    /**
	 * transform the local date time (yyyy/mm/dd HH:mm) to the new format
	 * (yyyy-mm-dd)
	 * 
	 * @param date
	 * @return new date format(String)
	 */

	private static String transformTime(LocalDateTime date) {

		String result = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth()
		+"  "+date.getHour()+":"+date.getMinute()+":"+date.getSecond();

		return result;
	}
	

}
