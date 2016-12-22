package vo.promotion;

import utils.info.promotion.PromotionInfo;
import utils.promotion.Promotion;

@SuppressWarnings("serial")
public abstract class PromotionVo extends PromotionInfo {
	protected Promotion promotion;

	PromotionVo(Scope scope) {
		super(scope);
	}

	PromotionVo(Scope scope, Promotion promotion) {
		super(scope);
		this.promotion = promotion;
	}

	/**
	 * @param id
	 *            to be set id
	 */
	PromotionVo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	PromotionVo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	PromotionVo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	PromotionVo setPromotion(Promotion promotion) {
		this.promotion = promotion;
		return this;
	}

	public String description() {
		return promotion.description();
	}

	@Override
	public String getContentString() {
		return promotion.toString();
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public String getDescription() {
		return name + (name.length() == 0 ? ": " : "") + promotion.description();
	}
}
