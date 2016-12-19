package utils.promotion;

import static utils.exception.StaticExceptionFactory.illegalArgEx;

import utils.promotion.applier.Applier;
import utils.promotion.applier.ApplierBuilder;
import utils.promotion.applier.How;
import utils.promotion.trigger.Trigger;
import utils.promotion.trigger.TriggerBuilder;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;

public class PromotionBuilder {
	private Trigger trigger;
	private Applier applier;

	public static final TriggerBuilder when(WebsiteWhen when) {
		return new TriggerBuilder(when);
	}

	public static final TriggerBuilder when(HotelWhen when) {
		return new TriggerBuilder(when);
	}

	public static final ApplierBuilder how(How how) {
		return new ApplierBuilder(how);
	}

	/**
	 * @param trigger
	 *            to be set trigger
	 * @return
	 */
	public PromotionBuilder setTrigger(Trigger trigger) {
		this.trigger = trigger;
		return this;
	}

	/**
	 * @param applier
	 *            to be set applier
	 * @return
	 */
	public PromotionBuilder setApplier(Applier applier) {
		this.applier = applier;
		return this;
	}

	public boolean isReady() {
		return applier != null && trigger != null;
	}

	public Promotion build() {
		if (!isReady())
			throw illegalArgEx("Not set up");

		return new Promotion(applier, trigger);
	}

	public static final Promotion parseString(String src) throws Exception {
		String[] strs = src.split("@v@");
		if (strs.length != 2)
			throw illegalArgEx("Promotion source string");

		return new Promotion(ApplierBuilder.parseString(strs[0]), TriggerBuilder.parseString(strs[1]));
	}
}