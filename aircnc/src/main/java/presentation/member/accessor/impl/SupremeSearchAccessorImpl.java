package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.SupremeSearchAccessor;

public class SupremeSearchAccessorImpl implements SupremeSearchAccessor{
	
	private static SupremeSearchAccessor instance;
	
	private int year;
	private int month;
	private int day;
	private int lowPrice;
	private int highPrice;
	private String roomType;
	private boolean empty;
	private double grade;
	private int star;
	
	public static final SupremeSearchAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SupremeSearchAccessorImpl();
	}
	
	public static final SupremeSearchAccessor getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
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
		this.roomType = type;
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
	public void setStar(int star) {
		this.star = star;
	}

}
