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
	private LocalDateTime enterTime;
	private LocalDateTime leaveTime;
	private String roomType;
	private LocalDateTime latestExecuteTime;
	private int peopleNumber;
	private boolean hasChildren;
	private HotelVo hotelVo;
	private OrderVo OrderVo;

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
	public void setEnterTime(LocalDateTime enterTime) {
		this.enterTime = enterTime;
	}

	@Override
	public void setLeaveTime(LocalDateTime leaveTime) {
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
		// TODO 填写完整信息
		int stayDays = (int) (leaveTime.toLocalDate().toEpochDay() - enterTime.toLocalDate().toEpochDay());
		return new OrderVoBuilder().
				setLastTime(latestExecuteTime).setRoomNumber(roomNumber)
				.setStatus(OrderStatus.UNEXECUTED).setEntryTime(enterTime)
				.setRoomType(roomType).setPeopleNumber(peopleNumber)
				.setHasChildren(hasChildren).setStayDays(stayDays).setHotel(hotelVo);
	}


	@Override
	public void setHotel(HotelVo vo) {
		this.hotelVo = vo;
	}


	@Override
	public OrderVo getCompleteOrder() {
		return OrderVo;
	} 


	@Override
	public void setCompleteOrder(OrderVo vo) {
		this.OrderVo = vo;
	}
}
