package po.order.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import utils.info.order.comment.CommentInfo;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;

public class CommentPo extends CommentInfo {
	protected CommentPo() {
		super();
	}

	protected CommentPo(int grade) {
		super(grade);
	}

	CommentPo setHotelId(int id) {
		this.hotelId = id;
		return this;
	}

	CommentPo setMemberId(String id) {
		this.memberId = id;
		return this;
	}

	CommentPo setContent(String content) {
		this.content = content;
		return this;
	}

	CommentPo setCheckInTime(LocalDate checkInTime) {
		this.checkInTime = checkInTime;
		return this;
	}

	CommentPo setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
		return this;
	}
	
	CommentPo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	CommentPo setGrade(int grade) {
		this.grade = grade;
		return this;
	}

	public void show() {
		System.out.println(this.getHotelId());
		System.out.println(this.memberId);
		System.out.println(this.hotelId);
		System.out.println(this.grade);
		System.out.println(this.content);
	}

	public CommentVo toCommentVo() {
		CommentVo vo = new CommentVoBuilder().setCheckInTime(checkInTime).setCommentTime(commentTime)
				.setContent(content).setHotelID(hotelId).setMemberID(memberId)
				.setGrade(grade).setOrderId(orderId).getCommentInfo();
		return vo;
	}

}
