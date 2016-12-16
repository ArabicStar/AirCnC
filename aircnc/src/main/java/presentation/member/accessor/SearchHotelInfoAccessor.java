package presentation.member.accessor;

import utils.condition.Condition;

public interface SearchHotelInfoAccessor{
	
	//这里我要知道condition的生成方法
	
	public String getHotelName();
	
	public Condition getScope();
	
	public void setScope(String scope);
	
	public void setName(String name);
}
