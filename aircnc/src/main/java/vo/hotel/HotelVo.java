package vo.hotel;

import java.util.Set;

import utils.info.hotel.HotelInfo;

public class HotelVo extends HotelInfo {

	private Set<RoomVo> rooms;

	public String getName() {
		if (isValid())
			return this.name;
		return null;
	}

	Set<RoomVo> getRooms() {
		if (isValid())
			return this.rooms;
		return null;
	}

	HotelVo setRooms(Set<RoomVo> rooms) {
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
		for (RoomVo vo : rooms) {
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
		for (RoomVo vo : rooms) {
			if (price == "") {
				price = Double.toString(vo.getPrice()) + "元";
			} else {
				price = price + "\n" + Double.toString(vo.getPrice()) + "元";
			}
		}
		return price;
	}

}
