package data.dao.impl.order;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.util.ArrayList;
import java.util.List;

import data.dao.order.CommentDao;
import data.dao.query.CommentQueryDao;
import po.order.OrderPo;
import po.order.comment.CommentPo;
import utils.info.order.OrderInfoTemplate;
import vo.order.comment.CommentVo;

public enum CommentDaoImpl implements CommentQueryDao, CommentDao{
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

	@Override
	public boolean addComment(CommentPo commentPo) {
		if (commentPo == null) {
			return false;
		}
		if(!OrderInfoTemplate.checkOrderId(commentPo.getOrderId_c())) {
			System.err.println("订单号长度应该大于等于16位，且长度为偶数");
			throw illegalArgEx("CommentPo orderId");
		}
		return execute(session -> {
			Boolean flag = Boolean.FALSE;
			if (flag = Boolean.valueOf(session.get(CommentPo.class, commentPo.getOrderId_c()) == null)) {
				session.save(commentPo);
			}
			return flag;
		});
	}
	
	@Override
	public boolean deleteComment(CommentPo commentPo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentPo getCommentByOrderId(String orderId) {
		if(!OrderInfoTemplate.checkOrderId(orderId)) {
			System.err.println("订单号长度应该大于等于16位，且长度为偶数");
			throw illegalArgEx("CommentPo orderId");
		}
		return execute(session -> {
			return (CommentPo) session.get(CommentPo.class, orderId);
		});
	}

	@Override
	public boolean updateComment(CommentPo commentPo) {
		if (commentPo == null) {
			return false;
		}
		if(!OrderInfoTemplate.checkOrderId(commentPo.getOrderId_c())) {
			System.err.println("订单号长度应该大于等于16位，且长度为偶数");
			throw illegalArgEx("CommentPo orderId");
		}
		
		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			if (flag = Boolean.valueOf(session.get(OrderPo.class, commentPo.getOrderId_c()) == null)) {
				session.update(commentPo);
			}
			return flag;
		});
	}

}
