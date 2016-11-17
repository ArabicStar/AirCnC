package utils.info.hotel;

public abstract class HotelInfoBuilder extends HotelInfoTemplate{
	public HotelInfoBuilder(HotelInfo info){
		this.setID(info.getID()).setName(info.getName()).setScope(info.getScope())
		.setLocation(info.getLocaiton()).setStar(info.getStar()).setGrade(info.getGrade());
	}
	
	protected HotelInfoBuilder(){
		
	}
	
	
	public HotelInfoBuilder setID(String id) {
		if (checkID(id))
			this.id = id;
		return this;
	}

	public abstract HotelInfoBuilder setName(String name);
	
	public HotelInfoBuilder setScope(String scope) {
		if (checkHotelScope(scope))
			this.scope = scope;
		return this;
	}
	
	public HotelInfoBuilder setLocation(String locaiton) {
		if (checkHotelLocation(locaiton))
			this.location = locaiton;
		return this;
	}
	
	public HotelInfoBuilder setIntro(String intro) {
		if (checkHotelScope(intro))
			this.introduction = intro;
		return this;
	}
	
	public HotelInfoBuilder setStar(int star) {
		if (checkHotelStar(star))
			this.star = star;
		return this;
	}	
	
	
	public HotelInfoBuilder setGrade(Double grade) {
		if (checkHotelGrade(grade))
			this.grade = grade;
		return this;
	}
	
	public boolean isReady(){
		return id != null && name != null && scope != null && location != null && introduction != null ;
	}
	
	public abstract HotelInfo getHotelInfo();
}
