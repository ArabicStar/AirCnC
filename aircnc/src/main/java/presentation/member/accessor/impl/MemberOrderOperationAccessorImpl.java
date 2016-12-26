package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;

import presentation.member.accessor.MemberOrderOperationAccessor;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;

public class MemberOrderOperationAccessorImpl implements MemberOrderOperationAccessor {
	
	private static MemberOrderOperationAccessor instance;
	
	private OrderVoBuilder order;
	private OrderVo vo;
	
	public static final MemberOrderOperationAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberOrderOperationAccessorImpl();
	}
	
	public static final MemberOrderOperationAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	@Override
	public OrderVo getOrder(){
		return vo;
	}

	@Override
	public void setComment(OrderVo vo, double rate, String content) {
		if(order == null)
			throw accessorNotReadyEx();
		else{
			CommentVo comment = new CommentVoBuilder(vo).setContent(content).setGrade((int)rate)
					.setCommentTime(LocalDateTime.now()).getCommentInfo();
			OrderVoBuilder builder = new OrderVoBuilder(vo).setComment(comment);
			this.order = builder;
		}
			
	}

	@Override
	public void setAppeal(OrderVo vo, String content) {
		if(order == null)
			throw accessorNotReadyEx();
		else{
			OrderVoBuilder builder = new OrderVoBuilder(vo).setAppeal(content);
			this.order = builder;
		}
	}

	@Override
	public void setCancel(OrderVo vo) {
		this.vo = vo;
	}

	@Override
	public OrderVo getCanceledOrder() {
		return vo;
	}

	@Override
	public void setOrder(OrderVo vo) {
		this.vo = vo;
	}

}
