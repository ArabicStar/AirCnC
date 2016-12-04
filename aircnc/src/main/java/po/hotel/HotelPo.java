package po.hotel;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.HotelInfo;

public class HotelPo extends HotelInfo{
	protected int passwordHash;
	protected Set<RoomPo> rooms;
	
	
	@Override
	public String getName() {
		if (isValid())
			return StringUtils.deleteWhitespace(this.name);
		return null;
	}
	
	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}
	
	public Set<RoomPo> getRooms(){
		if (isValid())
			return this.rooms;
		return null;
	}
	
	public HotelPo setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}
	
	public HotelPo setRooms(Set<RoomPo> rooms){
		this.rooms = rooms;
		return this;
	}
	
	
	public HotelPo setId(int id){
		this.id = id;
		return this;
	}
	
	public HotelPo setName(String name){
		this.name = name;
		return this;
	}
	
	public HotelPo setScope(String scope){
		this.scope = scope;
		return this;
	}
	
	public HotelPo setLocation(String location){
		this.location = location;
		return this;
	}
	
	public HotelPo setIntroduction(String intro){
		this.introduction = intro;
		return this;
	}
	
	public HotelPo setStar(int star){
		this.star = star;
		return this;
	}
	
	public HotelPo setGrade(double grade){
		this.grade = grade;
		return this;
	}
	
	public HotelPo setEquipment(String equipment){
		this.equipment = equipment;
		return this;
	}
	
	
}
