package vo.promotion;

import static utils.exception.StaticExceptionFactory.illegalStateException;

import utils.info.promotion.PromotionInfo;
import utils.info.promotion.PromotionInfoBuilder;
import utils.promotion.Promotion;
import utils.promotion.PromotionBuilder;
import utils.promotion.applier.Applier;
import utils.promotion.applier.ApplierBuilder;
import utils.promotion.applier.How;
import utils.promotion.trigger.Trigger;
import utils.promotion.trigger.TriggerBuilder;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;

public class PromotionVoBuilder extends PromotionInfoBuilder {
	protected ApplierBuilder applierBuilder;
	protected TriggerBuilder triggerBuilder;
	private Promotion prom;
	private int hotelId;

	public PromotionVoBuilder(Scope scope) {
		super(scope);
	}

	public PromotionVoBuilder(Scope scope, Promotion prom) {
		super(scope);
		this.prom = prom;
	}

	public PromotionVoBuilder(PromotionInfo info) {
		super(info);

		if (info instanceof PromotionVo)
			this.prom = ((PromotionVo) info).getPromotion();
		else {
			try {
				this.prom = PromotionBuilder.parseString(info.getContentString());
			} catch (Exception e) {
				e.printStackTrace();
				this.prom = null;
			}
		}
	}

	public TriggerBuilder when(WebsiteWhen when) {
		if (scope != Scope.Website)
			return null;

		return triggerBuilder = PromotionBuilder.when(when);
	}

	public TriggerBuilder when(HotelWhen when) {
		if (scope != Scope.Hotel)
			return null;

		return triggerBuilder = PromotionBuilder.when(when);
	}

	public ApplierBuilder how(How how) {
		return applierBuilder = PromotionBuilder.how(how);
	}

	/**
	 * @param id
	 *            to be set id
	 */
	public PromotionVoBuilder setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * @param name
	 *            to be set name
	 */
	public PromotionVoBuilder setName(String name) {
		super.setName(name);
		return this;
	}

	/**
	 * @param isPractical
	 *            to be set isPractical
	 */
	public PromotionVoBuilder setPractical(boolean isPractical) {
		super.setPractical(isPractical);
		return this;
	}

	public PromotionVoBuilder setHotelId(int hotelId) {
		super.setHotelId(hotelId);
		return this;
	}

	public boolean isReady() {
		return super.isReady() && applierBuilder.isReady() && triggerBuilder.isReady();
	}

	@Override
	public PromotionVo getPromotionInfo() {
		if (!isReady() && prom == null)
			throw illegalStateException("Not set up");

		Applier applier = applierBuilder.isReady() ? applierBuilder.build() : prom.getApplier();
		Trigger trigger = triggerBuilder.isReady() ? triggerBuilder.build() : prom.getTrigger();

		prom = new Promotion(applier, trigger);

		PromotionVo vo = null;
		if (scope == Scope.Website)
			vo = new WebsitePromotionVo(prom).setName(name).setPractical(isPractical).setId(id);
		else
			vo = new HotelPromotionVo(prom).setName(name).setPractical(isPractical).setId(id).setHotelId(hotelId);

		if (!isActive)
			vo.expire();

		return vo;
	}
}
