package vo.commrnt;

import utils.info.comment.CommentInfo;
import utils.info.comment.CommentInfoBuilder;

public class CommentVoBuilder extends CommentInfoBuilder{

	private static final CommentVo INVALID_COMMENT_VO;
	static {
		INVALID_COMMENT_VO = new CommentVo(1);
		INVALID_COMMENT_VO.invalidate();
	}

	public static final CommentVo getInvalidInfo() {
		return INVALID_COMMENT_VO;
	}
	
	public CommentVoBuilder(CommentInfo info) {
		super(info);
	}

	@Override
	public CommentInfo getCommentInfo() {
		if (!isReady())
			return null;

		return new CommentVo(grade).setHotelId(hotelId).setMemberId(memberId).setCheckInTime(checkInTime)
				.setCommentTime(commentTime);
		
	}
	
	

}
