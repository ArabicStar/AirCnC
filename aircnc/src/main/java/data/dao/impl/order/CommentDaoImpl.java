package data.dao.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.query.CommentQueryDao;
import vo.order.comment.CommentVo;

public enum CommentDaoImpl implements CommentQueryDao{
	INSTANCE;
	
	/**
	 * find comment data in table comment
	 * find number data in table member
	 * 第零步获取po
	 * 第一步把获取的po转换成vo(完成)，早上修复了这一个bug
	 * 第二步把vo的用户名和等级属性设置好
	 * 第三步把vo添加到list
	 */
	@Override
	public List<CommentVo> findByHotelId(int hotelId) {
		// TODO Auto-generated method stub
		List<CommentVo> list = new ArrayList<>();
		return list;
	}

}
