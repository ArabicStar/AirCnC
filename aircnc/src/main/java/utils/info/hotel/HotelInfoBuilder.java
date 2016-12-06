package utils.info.hotel;

public abstract class HotelInfoBuilder extends HotelInfoTemplate {

	public HotelInfoBuilder(HotelInfo info) {
		this.setID(info.getId()).setName(info.getName()).setScope(info.getScope()).setLocation(info.getLocation())
				.setStar(info.getStar()).setGrade(info.getGrade()).setEquipment(info.getEquipment());
	}

	public HotelInfoBuilder() {

	}

	public HotelInfoBuilder setID(int id) {
		if (id > 0)
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
			this.location = equipment;
		return this;
	}

	public boolean isReady() {
		return name != null&& star>0;
	}

	public abstract HotelInfo getHotelInfo();
}
