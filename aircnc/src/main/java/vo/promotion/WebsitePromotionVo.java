package vo.promotion;

import utils.promotion.Promotion;

public class WebsitePromotionVo extends PromotionVo {

	WebsitePromotionVo(Promotion promotion) {
		super(Scope.Website, promotion);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	WebsitePromotionVo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	WebsitePromotionVo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	WebsitePromotionVo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	WebsitePromotionVo setPromotion(Promotion promotion) {
		this.promotion = promotion;
		return this;
	}

	@Override
	public int getHotelId() {
		return -1;
	}

}
