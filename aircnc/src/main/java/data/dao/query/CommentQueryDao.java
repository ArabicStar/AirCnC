package data.dao.query;

import java.util.List;

import vo.order.comment.CommentVo;

public interface CommentQueryDao {
	public List<CommentVo> findByHotelId(int hotelId);
}
