package po.promotion;

public class HotelPromotionPo extends PromotionPo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2386804550537356167L;

	public HotelPromotionPo() {
		super(Scope.Hotel);
	}

	public HotelPromotionPo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public HotelPromotionPo setId(long id) {
		this.id = id;
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public HotelPromotionPo setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public HotelPromotionPo setPractical(boolean isPractical) {
		this.isPractical = isPractical;
		return this;
	}

	/**
	 * @param contentString
	 *            to be set contentString
	 * @return
	 */
	public HotelPromotionPo setContentString(String contentString) {
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

	/**
	 * @return hotelId
	 */
	public int getHotelId() {
		return hotelId;
	}

}
