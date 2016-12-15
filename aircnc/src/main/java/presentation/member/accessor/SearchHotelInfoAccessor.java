package presentation.member.accessor;

public interface SearchHotelInfoAccessor{
	
	//这里我要知道condition的生成方法
	
	
	
	public void setScope(String scope);
	
	public void setName(String name);
	
	public void setYear(int year);
	
	public void setMonth(int month);
	
	public void setDay(int day);
	
	public void setLowPrice(int low);
	
	public void setHighPrice(int high);
	
	public void setRoomType(String type);
	
	public void setIsEmpty(boolean empty);
	
	public void setGrade(double grade);
	
	public void setStar(int star);
}
