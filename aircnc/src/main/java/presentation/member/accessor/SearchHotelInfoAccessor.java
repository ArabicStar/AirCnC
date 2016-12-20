package presentation.member.accessor;

import utils.condition.Condition;

public interface SearchHotelInfoAccessor{
	
	public Condition getHotelName();
	
	public Condition getScope();
	
	public void setScope(String scope);
	
	public void setName(String name);
}
