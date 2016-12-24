package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDate;

import presentation.member.accessor.HotelSearchAccessor;
import utils.condition.Condition;
import utils.condition.Condition.Type;
import utils.condition.ConditionBuilder;

public class HotelSearchAccessorImpl implements HotelSearchAccessor {

	private static HotelSearchAccessor instance;

	private int year;
	private int month;
	private int day;
	private int lowPrice;
	private int highPrice;
	private Type roomType;
	private boolean empty;
	private double grade;
	private int lowStar;
	private int highStar;

	private String scope;
	private String name;

	public static final HotelSearchAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelSearchAccessorImpl();
	}

	public static final HotelSearchAccessor getInstance() {
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
		switch (type) {
		case "所有":
			roomType = Type.全部;
			break;
		case "单人间":
			roomType = Type.单人间;
			break;
		case "双人间":
			roomType = Type.双人间;
			break;
		case "三人间":
			roomType = Type.三人间;
			break;
		case "其他":
			roomType = Type.其它;
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
