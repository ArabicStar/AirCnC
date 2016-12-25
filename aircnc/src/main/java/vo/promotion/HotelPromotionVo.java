package vo.promotion;

import utils.promotion.Promotion;

@SuppressWarnings("serial")
public class HotelPromotionVo extends PromotionVo {
	private int hotelId;

	public HotelPromotionVo(Promotion promotion) {
		super(Scope.Hotel, promotion);
		hotelId = Integer.MIN_VALUE;
	}

	/**
	 * @return hotelId
	 */
	public int getHotelId() {
		return hotelId;
	}

	/**
	 * @param id
	 *            to be set id
	 */
	HotelPromotionVo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	HotelPromotionVo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	HotelPromotionVo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	HotelPromotionVo setPromotion(Promotion promotion) {
		this.promotion = promotion;
		return this;
	}

	/**
	 * @param hotelId
	 *            to be set hotelId
	 */
	HotelPromotionVo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

}
