package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.MemberOrderOperationAccessor;

public class MemberOrderOperationAccessorImpl implements MemberOrderOperationAccessor {
	
	private static MemberOrderOperationAccessor instance;
	
	private String orderID;
	private String comment;
	private String appeal;
	
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
	public String getComment() {
		return comment;
	}

	@Override
	public String getAppeal() {
		return appeal;
	}
	
	@Override
	public String getOrderId(){
		return orderID;
	}

	@Override
	public void setComment(String id, String content) {
		this.orderID = id;
		this.comment = content;
	}

	@Override
	public void setAppeal(String id, String content) {
		this.orderID = id;
		this.appeal = content;
	}

	@Override
	public void setCancel(String id) {
		this.orderID = id;
	}

}
