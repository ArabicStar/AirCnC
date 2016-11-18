package vo.hotel;

import utils.info.hotel.HotelInfo;

public class HotelVo extends HotelInfo{
	
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}
	
	HotelVo setID(String id){
		this.id = id;		
		return this;
	}
	
	HotelVo setName(String name){
		this.name = name;
		return this;
	}
	
	HotelVo setScope(String scope){
		this.scope = scope;
		return this;
	}
	
	HotelVo setLocation(String location){
		this.location = location;
		return this;
	}
	
	HotelVo setIntro(String intro){
		this.introduction = intro;
		return this;
	}
	
	HotelVo setStar(int star){
		this.star = star;
		return this;
	}
	
	HotelVo setGrade(double grade){
		this.grade = grade;
		return this;
	}
	
	
}
