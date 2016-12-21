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

	public CommentPo setHotelId(int id) {
		this.hotelId = id;
		return this;
	}

	public CommentPo setMemberId(String id) {
		this.memberId = id;
		return this;
	}

	public CommentPo setContent(String content) {
		this.content = content;
		return this;
	}

	public CommentPo setCheckInTime(LocalDate checkInTime) {
		this.checkInTime = checkInTime;
		return this;
	}

	public CommentPo setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
		return this;
	}
	
	public CommentPo setOrderId_c(String orderId) {
		this.orderId_c = orderId;
		return this;
	}
	
	public CommentPo setGrade(int grade) {
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
				.setGrade(grade).setOrderId_c(orderId_c).getCommentInfo();
		return vo;
	}

}
