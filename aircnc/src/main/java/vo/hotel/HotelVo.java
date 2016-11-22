package vo.hotel;

import java.util.List;

import utils.info.hotel.HotelInfo;

public class HotelVo extends HotelInfo{
	
	private List<RoomVo> rooms;
	
	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}
	
	HotelVo setRooms(List<RoomVo> rooms){
		this.rooms = rooms;
		return this;
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
