package presentation.hotel.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.order.comment.CommentVo;

public class CommentModel {
	private final StringProperty memberId;
	private final StringProperty level;
	private final StringProperty checkInTime;
	private final StringProperty grade;
	private final StringProperty content;
	private final StringProperty commentTime;
	
	public CommentModel(){
		this(null);
	}
	
	public CommentModel(CommentVo vo){
		this.memberId = new SimpleStringProperty(vo.getMemberId());
		this.level = new SimpleStringProperty("Lv"+Integer.toString(vo.getMemberLevel()));
		this.checkInTime = new SimpleStringProperty("于"+vo.getCheckInTime().toString()+"入住");
		this.commentTime = new SimpleStringProperty(vo.getCommentTime().toString());
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
    
    public String getMemberId() {
        return memberId.get();
    }

    public void setMemberId(String newMemberId) {
        this.memberId.set(newMemberId);
    }

    public StringProperty memberIdProperty() {
        return memberId;
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
	

}
