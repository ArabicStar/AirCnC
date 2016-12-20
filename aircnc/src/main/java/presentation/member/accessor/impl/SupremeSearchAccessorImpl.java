package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import presentation.member.accessor.SupremeSearchAccessor;
import utils.condition.Condition;
import utils.condition.ConditionBuilder;
import utils.info.hotel.RoomTemplate.Type;

public class SupremeSearchAccessorImpl implements SupremeSearchAccessor {

	private static SupremeSearchAccessor instance;

	private int year;
	private int month;
	private int day;
	private int lowPrice;
	private int highPrice;
	private Set<Type> roomType;
	private boolean empty;
	private double grade;
	private int lowStar;
	private int highStar;

	private String scope;
	private String name;

	public static final SupremeSearchAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SupremeSearchAccessorImpl();
	}

	public static final SupremeSearchAccessor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	@Override
	public Condition getCondition() {
		ConditionBuilder builder = new ConditionBuilder();
		if (this.name == null && this.scope == null) {
			builder.rankGreaterThan(grade);
			builder.starBetween(lowStar, highStar);
			builder.roomNeed(roomType);
			builder.avaliable(empty);
			builder.since(LocalDate.of(year, month, day));
			builder.priceBetween(lowPrice, highPrice);
		}else if(this.name != null){
			builder.nameLike(this.name);
		}else if(this.scope != null){
			builder.scopeLike(this.scope);
		}

		return builder.buildCondition();
	}

	public static boolean isLaunched() {
		if (instance == null)
			return false;
		else
			return true;
	}

	@Override
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public void setLowPrice(int low) {
		this.lowPrice = low;
	}

	@Override
	public void setHighPrice(int high) {
		this.highPrice = high;
	}

	@Override
	public void setRoomType(String type) {
		this.roomType = new HashSet<Type>();
		switch (type) {
		case "所有":
			roomType.add(Type.单人间);
			roomType.add(Type.双人间);
			roomType.add(Type.三人间);
			roomType.add(Type.其它);
			break;
		case "单人间":
			roomType.add(Type.单人间);
			break;
		case "双人间":
			roomType.add(Type.双人间);
			break;
		case "三人间":
			roomType.add(Type.三人间);
			break;
		case "其他":
			roomType.add(Type.其它);
			break;
		}
	}

	@Override
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public void setStarRange(int low, int high) {
		this.lowStar = low;
		this.highStar = high;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
