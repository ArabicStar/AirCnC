package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.MemberCommentAccessor;

public class MemberCommentAccessorImpl implements MemberCommentAccessor{
	
	private static MemberCommentAccessor instance;
	
	private double rate;
	private String comment;
	
	public static final MemberCommentAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberCommentAccessorImpl();
	}
	
	public static final MemberCommentAccessor getInstance(){
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
	public double getRating() {
		if(rate == 0)
			throw accessorNotReadyEx();
		return rate;
	}

	@Override
	public String getComment() {
		if(comment == null)
			throw accessorNotReadyEx();
		return comment;
	}

	@Override
	public void setRating(double rate) {
		this.rate = rate;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
