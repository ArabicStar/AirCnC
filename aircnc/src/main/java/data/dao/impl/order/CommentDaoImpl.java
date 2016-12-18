package data.dao.impl.order;

import java.util.List;

import data.dao.query.CommentQueryDao;
import vo.order.comment.CommentVo;

public enum CommentDaoImpl implements CommentQueryDao{
	INSTANCE;
	
	/**
	 * find comment data in table comment
	 * find number data in table member
	 */
	@Override
	public List<CommentVo> findByHotelId(int hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

}
