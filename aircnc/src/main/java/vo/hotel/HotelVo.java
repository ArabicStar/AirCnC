package vo.hotel;

import java.util.Set;
import java.util.stream.Collectors;

import utils.condition.Condition.Type;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;

@SuppressWarnings("serial")
public class HotelVo extends HotelInfo {

	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}

	HotelVo setRooms(Set<Room> rooms) {
		this.rooms = rooms;
		return this;
	}

	HotelVo setID(int id) {
		this.id = id;
		return this;
	}

	HotelVo setName(String name) {
		this.name = name;
		return this;
	}

	HotelVo setScope(String scope) {
		this.scope = scope;
		return this;
	}

	HotelVo setLocation(String location) {
		this.location = location;
		return this;
	}

	HotelVo setIntro(String intro) {
		this.introduction = intro;
		return this;
	}

	HotelVo setStar(int star) {
		this.star = star;
		return this;
	}

	HotelVo setGrade(double grade) {
		this.grade = grade;
		return this;
	}

	HotelVo setEquipment(String equipment) {
		this.equipment = equipment;
		return this;
	}

	public String getStringRoomName() {
		String type = "";
		for (Room vo : rooms) {
			if (type == "") {
				type = vo.getName();
			} else {
				type = type + "\n" + vo.getName();
			}
		}
		return type;
	}

	public String getStringRoomPrice() {
		String price = "";
		for (Room vo : rooms) {
			if (price == "") {
				price = Double.toString(vo.getPrice()) + "元";
			} else {
				price = price + "\n" + Double.toString(vo.getPrice()) + "元";
			}
		}
		return price;
	}
	
	public double getLowestPrice(){
		return rooms.stream().mapToDouble(Room::getPrice).min().getAsDouble();
	}
	
	public double getHighestPrice(){
		return rooms.stream().mapToDouble(Room::getPrice).max().getAsDouble();
	}
	
	public boolean hasRoom(Type type,boolean isAvailable){
		if(type.ordinal()>3||type.ordinal()==0){
			return rooms.stream().filter(r->r.getRoomNum()>0||!isAvailable).
					mapToInt(Room::getPeopleNum).max().getAsInt()>type.ordinal();
		}else{
			return !rooms.stream().filter(r->r.getRoomNum()>0||!isAvailable).
					filter(r->r.getPeopleNum()==type.ordinal()).
					collect(Collectors.toList()).isEmpty();
					
		}
	}

}
