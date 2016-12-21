package utils.info.hotel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class HotelInfoBuilder extends HotelInfoTemplate {

	public HotelInfoBuilder() {
		id = 0;
		name = BLANK;
		scope = BLANK;
		location = BLANK;
		introduction = BLANK;
		rooms = new HashSet<>();
		star = 1;
		grade = 0.0;
		equipment = BLANK;
	}

	public HotelInfoBuilder(HotelInfo info) {
		this();
		this.setID(info.getId()).setName(info.getName()).setScope(info.getScope()).setLocation(info.getLocation())
				.setStar(info.getStar()).setGrade(info.getGrade()).setEquipment(info.getEquipment())
				.setRooms(info.getRooms());
	}

	public HotelInfoBuilder setID(int id) {
		if (id >= 0)
			this.id = id;
		return this;
	}

	public abstract HotelInfoBuilder setName(String name);

	public HotelInfoBuilder setScope(String scope) {
		if (scope != null)
			this.scope = scope;
		return this;
	}

	public HotelInfoBuilder setLocation(String locaiton) {
		if (locaiton != null)
			this.location = locaiton;
		return this;
	}

	public HotelInfoBuilder setIntro(String intro) {
		if (intro != null)
			this.introduction = intro;
		return this;
	}

	public HotelInfoBuilder setStar(int star) {
		if (checkHotelStar(star))
			this.star = star;
		return this;
	}

	public HotelInfoBuilder setGrade(Double grade) {
		if (grade != 0)
			this.grade = grade;
		return this;
	}

	public HotelInfoBuilder setEquipment(String equipment) {
		if (equipment != null)
			this.equipment = equipment;
		return this;
	}

	public HotelInfoBuilder setRooms(Set<Room> rooms) {
		if (rooms != null)
			this.rooms = rooms.stream().map(r -> new RoomBuilder(r).getRoomInfo()).collect(Collectors.toSet());
		else
			this.rooms.clear();
		return this;
	}

	public boolean isReady() {
		return name != null && star > 0;
	}

	public abstract HotelInfo getHotelInfo();
}
