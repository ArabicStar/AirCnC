package vo.hotel;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import po.hotel.HotelPo;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;

public class HotelVoBuilder extends HotelInfoBuilder{
	private Set<RoomVo> rooms;

	private static final HotelVo INVALID_HOTEL_VO;
	static {
		INVALID_HOTEL_VO = new HotelVo();
		INVALID_HOTEL_VO.invalidate();
	}
	
	public static final HotelVo invalidInfo() {
		return INVALID_HOTEL_VO;
	}
	
	public HotelVoBuilder(HotelInfo info){
		this.setID(info.getId()).setName(info.getName()).setScope(info.getScope()).setLocation(info.getLocation())
		.setStar(info.getStar()).setGrade(info.getGrade()).setEquipment(info.getEquipment());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}
	
	public HotelVoBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	HotelVoBuilder setRooms(Set<RoomVo> rooms){
		this.rooms = rooms;
		return this;
	}
	
	@Override
	public HotelVoBuilder setName(String name) {
		if (checkHotelName(name))
			this.name = name;
		return this;
	}

	@Override
	public HotelVo getHotelInfo() {
		if (!isReady())
			throw new IllegalArgumentException("Invalid HotelInfo Instance");

		return new HotelVo().setID(id).setName(name).setScope(scope).setLocation(location)
				.setIntro(introduction).setStar(star).setGrade(grade).setEquipment(equipment)
				.setRooms(rooms);
	}

	
	@Override
	public HotelVoBuilder setScope(String scope) {
		super.setScope(scope);
		return this;
	}
	
	@Override
	public HotelVoBuilder setID(int id) {
		this.id = id;
		return this;
	}
	
	@Override
	public HotelVoBuilder setLocation(String locaiton) {
		super.setLocation(locaiton);
		return this;
	}
	
	
	@Override
	public HotelVoBuilder setIntro(String intro) {
		super.setIntro(intro);
		return this;
	}
	
	
	@Override
	public HotelVoBuilder setStar(int star) {
		super.setStar(star);
		return this;
	}	
	
	
	@Override
	public HotelVoBuilder setGrade(Double grade) {
		super.setGrade(grade);
		return this;
	}
	
	@Override
	public HotelVoBuilder setEquipment(String equipment) {
		super.setEquipment(equipment);
		return this;
	}

}
