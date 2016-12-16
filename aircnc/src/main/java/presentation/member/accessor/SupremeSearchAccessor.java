package presentation.member.accessor;

import utils.condition.Condition;

public interface SupremeSearchAccessor {
	//这里我等一个酒店高级query
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
}
