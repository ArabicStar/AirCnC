package vo.hotel;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;

public class HotelVoBuilder extends HotelInfoBuilder{

	private static final HotelVo INVALID_HOTEL_VO;
	static {
		INVALID_HOTEL_VO = new HotelVo();
		INVALID_HOTEL_VO.invalidate();
	}
	
	public static final HotelVo getInvalidInfo() {
		return INVALID_HOTEL_VO;
	}
	
	public HotelVoBuilder(HotelInfo info){
		super(info);
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
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
				.setIntro(introduction).setStar(star).setGrade(grade);
	}
	
	@Override
	public HotelVoBuilder setScope(String scope) {
		super.setScope(scope);
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

}
