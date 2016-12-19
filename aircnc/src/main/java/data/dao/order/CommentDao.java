package data.dao.order;

import po.order.comment.CommentPo;

public interface CommentDao {
	/**
	 * 增加一条评论记录
	 * @param commentPo
	 */
	public boolean addComment(CommentPo commentPo);
	
	/**
	 * 删除一条评论记录
	 * @param commentPo
	 */
	public boolean deleteComment(CommentPo commentPo);
	
	/**
	 * 获取一条评论记录
	 * @param OrderId
	 */
	public CommentPo getCommentByOrderId(String orderId);
	
	/**
	 * 更新一条订单记录
	 * @param commentPo
	 */
	public boolean updateComment(CommentPo commentPo);
	
}
