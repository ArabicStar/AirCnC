package po.order.comment;

import utils.info.order.comment.CommentInfo;
import utils.info.order.comment.CommentInfoBuilder;
import vo.order.comment.CommentVo;

public class CommentPoBuilder extends CommentInfoBuilder{

	private static final CommentPo INVALID_COMMENT_PO;
	static {
		INVALID_COMMENT_PO = new CommentPo(1);
		INVALID_COMMENT_PO.invalidate();
	}

	public static final CommentPo getInvalidInfo() {
		return INVALID_COMMENT_PO;
	}
	
	
	public CommentPoBuilder(CommentInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommentPo getCommentInfo() {
		if (!isReady())
			return new CommentPo(0);

		return new CommentPo(grade).setHotelId(hotelId).setMemberId(memberId).
				setCheckInTime(checkInTime).setCommentTime(commentTime);
	}

}
