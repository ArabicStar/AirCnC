package po.hotel;

import java.util.Set;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;

public class HotelPoBuilder extends HotelInfoBuilder {
	private int passwordHash = Integer.MIN_VALUE;
	private Set<RoomPo> rooms = null;

	private static final HotelPo INVALID_HOTEL_PO;
	static {
		INVALID_HOTEL_PO = new HotelPo();
		INVALID_HOTEL_PO.invalidate();
	}

	/**
	 * Get an invalid HotelPo instance.
	 * 
	 * @return Invalid HotelPo instance
	 */
	public static final HotelPo getInvalidInfo() {
		return INVALID_HOTEL_PO;
	}

	/**
	 * Invalid HotelPo instance.<br>
	 * Usually, used to mark invalid access and operation, etc.<br>
	 */
	public HotelPoBuilder(HotelInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	public HotelPoBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public HotelPoBuilder setName(String name) {
		if (checkHotelName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");
		else
			throw new IllegalArgumentException("Wrong hotel name");

		return this;
	}

	@Override
	public HotelPo getHotelInfo() {
		if (!isReady() && passwordHash == Integer.MIN_VALUE)
			throw new IllegalStateException("Lack Of Info");

		return new HotelPo().setName(name).setPasswordHash(passwordHash).setScope(scope).setLocation(location)
				.setIntroduction(introduction).setStar(star).setGrade(grade).setRooms(rooms).setId(id);
	}

	/**
	 * @param passwordHash
	 * @return this instance
	 */
	public HotelPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	public HotelPoBuilder setRooms(Set<RoomPo> rooms) {
		this.rooms = rooms;
		return this;
	}

	@Override
	public HotelPoBuilder setScope(String scope) {
		super.setScope(scope);
		return this;
	}

	@Override
	public HotelPoBuilder setLocation(String locaiton) {
		super.setLocation(locaiton);
		return this;
	}

	@Override
	public HotelPoBuilder setIntro(String intro) {
		super.setIntro(intro);
		return this;
	}

	@Override
	public HotelPoBuilder setStar(int star) {
		super.setStar(star);
		return this;
	}

	@Override
	public HotelPoBuilder setGrade(Double grade) {
		super.setGrade(grade);
		return this;
	}

	@Override
	public HotelPoBuilder setID(int id) {
		super.setID(id);
		return this;
	}

	@Override
	public HotelPoBuilder setEquipment(String equipment) {
		super.setEquipment(equipment);
		return this;
	}

	public static final void updatePo(HotelPo from, HotelPo to) {
		if (from == null || to == null || from == to)
			return;

		if (from.getId() != to.getId() || !from.getName().equals(to.getName()))
			throw new IllegalArgumentException("HotelPoBuilder.updatePo - Different identifier or name");

		to.setPasswordHash(from.getPasswordHash()).setScope(from.getScope()).setLocation(from.getLocation())
				.setIntroduction(from.getIntroduction()).setStar(from.getStar()).setGrade(from.getGrade())
				.setRooms(from.getRooms()).setEquipment(from.getEquipment());
	}

}
