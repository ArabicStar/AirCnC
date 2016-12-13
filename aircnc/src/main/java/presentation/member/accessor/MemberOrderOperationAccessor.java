package presentation.member.accessor;


public interface MemberOrderOperationAccessor {
	
	public String getComment();
	
	public String getAppeal();
	
	public String getOrderId();
	
	public void setComment(String id, String content);
	
	public void setAppeal(String id, String content);
}
