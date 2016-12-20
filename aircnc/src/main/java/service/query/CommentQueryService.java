package service.query;

import java.util.List;

import vo.order.comment.CommentVo;

public interface CommentQueryService {
	public List<CommentVo> getHotelComments(int hotelId);
}
