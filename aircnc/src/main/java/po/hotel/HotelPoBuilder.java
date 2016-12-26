package po.hotel;

import static utils.exception.StaticExceptionFactory.illegalStateException;
import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import utils.info.hotel.HotelInfo;
import utils.info.hotel.HotelInfoBuilder;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;

@SuppressWarnings("serial")
public class HotelPoBuilder extends HotelInfoBuilder {
	private int passwordHash = Integer.MIN_VALUE;

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

	public HotelPoBuilder() {
		super();
	}

	/**
	 * Invalid HotelPo instance.<br>
	 * Usually, used to mark invalid access and operation, etc.<br>
	 */
	public HotelPoBuilder(HotelInfo info) {
		super(info);
		
		if (info instanceof HotelPo)
			setPasswordHash(((HotelPo) info).getPasswordHash());
	}

	@Override
	public HotelPo getHotelInfo() {
		if (!isReady())
			throw illegalStateException("HotelPoBuilder not set up");

		return new HotelPo().setId(id).setPasswordHash(passwordHash).setName(name).setScope(scope).setLocation(location)
				.setIntroduction(introduction).setStar(star).setGrade(grade).setRooms(rooms).setEquipment(equipment);
	}

	@Override
	public HotelPoBuilder setName(String name) {
		if (!checkHotelName(name))
			throw new IllegalArgumentException("Wrong hotel name");

		// insert blank space to avoid injection attack
		this.name = name.replaceAll("(.{1})", "$1 ");

		return this;
	}

	/**
	 * @param passwordHash
	 * @return this instance
	 */
	public HotelPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	@Override
	public HotelPoBuilder setRooms(Set<Room> rooms) {
		super.setRooms(rooms);
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

	/**
	 * 
	 * @param from
	 *            modified hotel information
	 * @param to
	 */
	public static final void updatePo(HotelPo from, HotelPo to) {
		if (from == null || to == null || from == to)
			return;

		if (from.getId() != to.getId() )
			throw inconsistentStatusEx();
		
		if(!from.getName().equals(to.getName())){
			to.setName(from.getName()).setStar(from.getStar());
		}

		if (from.getScope() != "") {
			to.setScope(from.getScope()).setLocation(from.getLocation()).setIntroduction(from.getIntroduction())
					.setEquipment(from.getEquipment());
		}

		if (from.getPasswordHash() != Integer.MIN_VALUE) {
			to.setPasswordHash(from.getPasswordHash());
		}

		if (from.getRooms() != null) {
			Iterator<Room> itTo = to.getRooms().iterator();
			Iterator<Room> itFrom = from.getRooms().iterator();
			Room newRoom = itFrom.next();

			while(itTo.hasNext()){
				Room oldRoom = itTo.next();

				if(newRoom.getName().equals(oldRoom.getName())){
					RoomBuilder.updatePo(newRoom, oldRoom);
					return;
				}
			}
			to.setRooms(from.getRooms());
				
		}	
	}

}
