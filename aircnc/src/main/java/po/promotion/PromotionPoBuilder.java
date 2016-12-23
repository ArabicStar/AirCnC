package po.promotion;

import static utils.exception.StaticExceptionFactory.illegalStateException;

import utils.info.promotion.PromotionInfo;
import utils.info.promotion.PromotionInfoBuilder;
import utils.promotion.PromotionBuilder;

@SuppressWarnings("serial")
public class PromotionPoBuilder extends PromotionInfoBuilder {
	private String contentString;

	public PromotionPoBuilder(PromotionInfo info) {
		super(info);
		this.contentString = info.getContentString();
	}

	@Override
	public boolean isReady() {
		if (!super.isReady())
			return false;

		// validate contentString: try to parse it
		boolean flag = false;
		try {
			PromotionBuilder.parseString(contentString);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public PromotionPo getPromotionInfo() {
		if (!isReady())
			throw illegalStateException("Not set up");

		if (scope == Scope.Hotel)
			return new HotelPromotionPo().setName(name).setContentString(contentString).setId(id)
					.setPractical(isPractical).setHotelId(hotelId);
		else
			return new WebsitePromotionPo().setName(name).setContentString(contentString).setId(id)
					.setPractical(isPractical);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public PromotionPoBuilder setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public PromotionPoBuilder setName(String name) {
		super.setName(name);
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public PromotionPoBuilder setPractical(boolean isPractical) {
		super.setPractical(isPractical);
		return this;
	}

	public PromotionPoBuilder setHotelId(int hotelId) {
		super.setHotelId(hotelId);
		return this;
	}

}
