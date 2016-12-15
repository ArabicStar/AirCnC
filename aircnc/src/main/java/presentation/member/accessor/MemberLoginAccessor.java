package presentation.member.accessor;

public interface MemberLoginAccessor {
	
	public String getId();
	
	public int getPasswordHash();
	
	public void setDeliveredId(String id);
	
	public void setDeliveredPassword(String password);
}
