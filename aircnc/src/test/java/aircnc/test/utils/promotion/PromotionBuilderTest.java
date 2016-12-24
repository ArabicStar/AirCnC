package aircnc.test.utils.promotion;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import utils.info.order.OrderInfo;
import utils.promotion.Promotion;
import utils.promotion.PromotionBuilder;
import utils.promotion.applier.Applier;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.Trigger;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;

public class PromotionBuilderTest {

	@Test
	public void testPromotionBuilder() {

		OrderInfo order = DataPrepareHelper.getAnOrder();

		LocalDateTime now = LocalDateTime.now();
		Trigger t = PromotionBuilder.when(WebsiteWhen.DURING_PERIOD).setParam(TriggerParams.FROM, now.plusDays(1))
				.setParam(TriggerParams.TO, now.plusDays(5)).build();
		Applier a = PromotionBuilder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.7).build();

		Promotion p = new PromotionBuilder().setApplier(a).setTrigger(t).build();

		System.out.println(p.description());

		if (p.canApplyTo(order))
			order = p.applyTo(order);

		assertEquals(70.0, order.getDiscountPrice(), 0.1);

		t = PromotionBuilder.when(HotelWhen.MULTI_ROOMS).setParam(TriggerParams.ROOM_NUM_THRESHOLD, 3).build();
		a = PromotionBuilder.how(How.CONST).setParam(ApplierParams.AMOUNT, 30.0).build();
		p = new PromotionBuilder().setApplier(a).setTrigger(t).build();

		if (p.canApplyTo(order))
			order = p.applyTo(order);

		System.out.println(p.description());

		assertEquals(40.0, order.getDiscountPrice(), 0.1);

		t = PromotionBuilder.when(HotelWhen.DURING_PERIOD).setParam(TriggerParams.FROM, now.plusDays(1))
				.setParam(TriggerParams.TO, now.plusDays(5)).build();
		a = PromotionBuilder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.9).build();
		p = new PromotionBuilder().setApplier(a).setTrigger(t).build();

		if (p.canApplyTo(order))
			order = p.applyTo(order);

		System.out.println(p.description());

		assertEquals(30.0, order.getDiscountPrice(), 0.1);

		t = PromotionBuilder.when(HotelWhen.DURING_PERIOD).setParam(TriggerParams.FROM, now.plusDays(10))
				.setParam(TriggerParams.TO, now.plusDays(20)).build();
		a = PromotionBuilder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.9).build();
		p = new PromotionBuilder().setApplier(a).setTrigger(t).build();

		if (p.canApplyTo(order))
			order = p.applyTo(order);

		System.out.println(p.description());

		assertEquals(30.0, order.getDiscountPrice(), 0.1);
	}
}
