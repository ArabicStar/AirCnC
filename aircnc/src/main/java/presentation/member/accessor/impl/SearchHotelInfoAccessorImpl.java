package presentation.member.accessor.impl;

import presentation.member.accessor.SearchHotelInfoAccessor;

public class SearchHotelInfoAccessorImpl implements SearchHotelInfoAccessor{
	
	private String scope;
	private String name;
	private int year;
	private int month;
	private int day;
	private int low;
	private int high;
	private String type;
	private boolean isEmpty;
	private double grade;
	private int star;
	

	@Override
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
		this.low = low;
	}

	@Override
	public void setHighPrice(int high) {
		this.high = high;
	}

	@Override
	public void setRoomType(String type) {
		this.type = type;
	}

	@Override
	public void setIsEmpty(boolean empty) {
		this.isEmpty = empty;
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