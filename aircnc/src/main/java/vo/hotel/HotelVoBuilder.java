package vo.hotel;

import java.util.Set;
import static utils.exception.StaticExceptionFactory.*;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;
import utils.info.hotel.Room;

@SuppressWarnings("serial")
public class HotelVoBuilder extends HotelInfoBuilder {

	private static final HotelVo INVALID_HOTEL_VO;
	static {
		INVALID_HOTEL_VO = new HotelVo();
		INVALID_HOTEL_VO.invalidate();
	}

	public static final HotelVo invalidInfo() {
		return INVALID_HOTEL_VO;
	}

	public HotelVoBuilder(HotelInfo info) {
		super(info);
	}

	public HotelVoBuilder() {
		super();
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
			throw illegalStateException("Hotel vo builder not set up");
		return new HotelVo().setID(id).setName(name).setScope(scope).setLocation(location).setIntro(introduction)
				.setStar(star).setGrade(grade).setEquipment(equipment).setRooms(rooms);
	}

	@Override
	public HotelVoBuilder setScope(String scope) {
		super.setScope(scope);
		return this;
	}

	@Override
	public HotelVoBuilder setRooms(Set<Room> rooms) {
		super.setRooms(rooms);
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
