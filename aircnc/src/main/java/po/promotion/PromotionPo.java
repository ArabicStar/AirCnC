package po.promotion;

import utils.info.promotion.PromotionInfo;

public abstract class PromotionPo extends PromotionInfo {
	protected String contentString;

	PromotionPo(Scope scope) {
		super(scope);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public PromotionPo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public PromotionPo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public PromotionPo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	/**
	 * @param contentString
	 *            to be set contentString
	 * @return
	 */
	public PromotionPo setContentString(String contentString) {
		this.contentString = contentString;
		return this;
	}

	/**
	 * @return contentString
	 */
	@Override
	public String getContentString() {
		return contentString;
	}

}
