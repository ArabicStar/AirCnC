package presentation.member.accessor.impl;

import presentation.member.accessor.SupremeSearchAccessor;

public class SupremeSearchAccessorImpl implements SupremeSearchAccessor{
	
	private int year;
	private int month;
	private int day;
	private int lowPrice;
	private int highPrice;
	private String roomType;
	private boolean empty;
	private int grade;
	private int star;
	
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
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public void setStar(int star) {
		this.star = star;
	}

}
