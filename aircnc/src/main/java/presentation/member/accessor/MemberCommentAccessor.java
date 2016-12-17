package presentation.member.accessor;

public interface MemberCommentAccessor {
	
	public double getRating();
	
	public String getComment();
	
	public String getId();

	public void setOrderId(String id);
	
	public void setRating(double rate);
	
	public void setComment(String comment);
	
}
