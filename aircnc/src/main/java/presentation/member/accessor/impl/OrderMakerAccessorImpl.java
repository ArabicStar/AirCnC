package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import presentation.member.accessor.OrderMakerAccessor;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class OrderMakerAccessorImpl implements OrderMakerAccessor{
	private static OrderMakerAccessor instance;

	private int roomNumber;
	private LocalDate enterTime;
	private LocalDate leaveTime;
	private String roomType;
	private LocalDateTime latestExecuteTime;
	private int peopleNumber;
	private boolean hasChildren;
	private HotelVo hotelVo;

	public static final OrderMakerAccessor launch() {
		if(instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new OrderMakerAccessorImpl();
	}


	public static final OrderMakerAccessor getIntance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}

	public static boolean isLaunch() {
		if (instance == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public void setEnterTime(LocalDate enterTime) {
		this.enterTime = enterTime;
	}

	@Override
	public void setLeaveTime(LocalDate leaveTime) {
		this.leaveTime = leaveTime;
	}

	@Override
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public void setLatestExecuteTime(LocalDateTime latestExecuteTime) {
		this.latestExecuteTime = latestExecuteTime;
	}

	@Override
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	@Override
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	@Override
	public OrderVoBuilder getMadeOrder() {
		if(instance == null) {
			throw accessorNotReadyEx();
		}
		LocalDateTime entryTime = LocalDateTime.of(enterTime, LocalTime.now());
		// TODO 填写完整信息
		int stayDays = (int) (leaveTime.toEpochDay() - enterTime.toEpochDay());
		return new OrderVoBuilder().
				setLastTime(latestExecuteTime).setRoomNumber(roomNumber)
				.setStatus(OrderStatus.UNEXECUTED).setEntryTime(entryTime)
				.setRoomType(roomType).setPeopleNumber(peopleNumber)
				.setHasChildren(hasChildren).setStayDays(stayDays).setHotel(hotelVo);
	}


	@Override
	public void setHotel(HotelVo vo) {
		this.hotelVo = vo;
	}
}
