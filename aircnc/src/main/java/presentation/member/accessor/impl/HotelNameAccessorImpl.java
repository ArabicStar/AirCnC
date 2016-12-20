package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.HotelNameAccessor;

public class HotelNameAccessorImpl implements HotelNameAccessor {

	private static HotelNameAccessor instance;

	private String name;

	public static final HotelNameAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelNameAccessorImpl();
	}

	public static final HotelNameAccessor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	public static boolean isLaunched() {
		if (instance == null)
			return false;
		else
			return true;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getHotelName() {
		return this.name;
	}

}
