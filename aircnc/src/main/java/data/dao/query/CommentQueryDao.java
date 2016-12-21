package data.dao.query;

import java.util.List;

import po.order.comment.CommentPo;

public interface CommentQueryDao {
	public List<CommentPo> findByHotelId(int hotelId);
}
