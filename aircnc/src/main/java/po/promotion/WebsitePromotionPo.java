package po.promotion;

public class WebsitePromotionPo extends PromotionPo {

	public WebsitePromotionPo() {
		super(Scope.Website);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public WebsitePromotionPo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public WebsitePromotionPo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public WebsitePromotionPo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	/**
	 * @param contentString
	 *            to be set contentString
	 * @return
	 */
	public WebsitePromotionPo setContentString(String contentString) {
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

	@Override
	public int getHotelId() {
		return -1;
	}

}
