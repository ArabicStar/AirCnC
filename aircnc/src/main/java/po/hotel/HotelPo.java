package po.hotel;

import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;

public class HotelPo extends HotelInfo {
	protected int passwordHash;

	@Override
	public String getName() {
		if (isValid())
			return name;
		return null;
	}

	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}

	public HotelPo setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	public HotelPo setId(int id) {
		this.id = id;
		return this;
	}

	public HotelPo setName(String name) {
		this.name = name;
		return this;
	}

	public HotelPo setScope(String scope) {
		this.scope = scope;
		return this;
	}

	public HotelPo setLocation(String location) {
		this.location = location;
		return this;
	}

	public HotelPo setIntroduction(String intro) {
		this.introduction = intro;
		return this;
	}

	public HotelPo setStar(int star) {
		this.star = star;
		return this;
	}

	public HotelPo setGrade(double grade) {
		this.grade = grade;
		return this;
	}

	public HotelPo setEquipment(String equipment) {
		this.equipment = equipment;
		return this;
	}

	public HotelPo setRooms(Set<Room> rooms) {
		for (Room room : rooms)
			room.setHotel(this);
		
		this.rooms = rooms;
		return this;
	}

}
