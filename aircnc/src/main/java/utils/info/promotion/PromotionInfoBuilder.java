package utils.info.promotion;

import static utils.exception.StaticExceptionFactory.illegalArgEx;

import utils.info.hotel.HotelInfoTemplate;

public abstract class PromotionInfoBuilder extends PromotionInfoTemplate {

	public PromotionInfoBuilder(Scope scope) {
		this.scope = scope;
		this.id = 0;
		this.name = BLANK;
		this.isPractical = true;
		this.isActive = true;
	}

	public PromotionInfoBuilder(PromotionInfo info) {
		if (!info.isValid())
			throw illegalArgEx("Invalid promotion info");

		this.scope = info.getScope();
		this.setId(info.getId()).setName(info.getName()).setPractical(info.getPractical()).setHotelId(hotelId);
		isActive = info.getActive();
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public PromotionInfoBuilder setId(long id) {
		if (!checkId(id))
			throw illegalArgEx("Promotion id");

		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public PromotionInfoBuilder setName(String name) {
		if (!checkName(name))
			throw illegalArgEx("Promotion name");

		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public PromotionInfoBuilder setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	public PromotionInfoBuilder setHotelId(int hotelId) {
		if (scope != Scope.Hotel)
			return this;

		if (!HotelInfoTemplate.checkID(hotelId))
			throw illegalArgEx("Hotel id in promtion");

		this.hotelId = hotelId;
		return this;
	}

	public boolean isReady() {
		return checkId(id) && checkName(name);
	}

	public abstract PromotionInfo getPromotionInfo();
}
