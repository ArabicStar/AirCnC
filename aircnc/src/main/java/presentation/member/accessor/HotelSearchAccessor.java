package presentation.member.accessor;

import utils.condition.Condition;

public interface HotelSearchAccessor {
	
	public Condition getCondition();
	
	public void setYear(int year);
	
	public void setMonth(int month);
	
	public void setDay(int day);
	
	public void setLowPrice(int low);
	
	public void setHighPrice(int high);
	
	public void setRoomType(String type);
	
	public void setEmpty(boolean empty);
	
	public void setGrade(double grade);
	
	public void setStarRange(int low,int high);
	
	public void setScope(String scope);
	
	public void setName(String name);
}
