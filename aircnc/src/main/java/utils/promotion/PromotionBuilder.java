package utils.promotion;

import static utils.exception.StaticExceptionFactory.*;
import java.time.LocalDate;

import utils.promotion.applier.Applier;
import utils.promotion.applier.ApplierBuilder;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.Trigger;
import utils.promotion.trigger.TriggerBuilder;
import utils.promotion.trigger.TriggerParams;
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

	public Promotion build() {
		if (applier != null && trigger != null)
			throw illegalArgEx("Not set up");

		return new Promotion(applier, trigger);
	}

	private void test() {
		LocalDate now = LocalDate.now();
		Trigger t = PromotionBuilder.when(WebsiteWhen.DURING_PERIOD).setParam(TriggerParams.FROM, now)
				.setParam(TriggerParams.TO, now.plusDays(1)).build();
		Applier a = PromotionBuilder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.3).build();
		Promotion p = new PromotionBuilder().setApplier(a).setTrigger(t).build();
	}
}