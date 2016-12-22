package po.order;

import static data.hibernate.Hibernator.execute;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.promotion.HotelPromotionPo;
import po.promotion.PromotionPo;
import po.promotion.PromotionPoBuilder;
import po.promotion.WebsitePromotionPo;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import utils.promotion.Promotion;
import utils.promotion.PromotionBuilder;
import utils.promotion.applier.Applier;
import utils.promotion.applier.ApplierParams;
import utils.promotion.applier.How;
import utils.promotion.trigger.Trigger;
import utils.promotion.trigger.TriggerParams;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteWhen;
import vo.promotion.PromotionVoBuilder;

public class OrderPoTest {
	public static void main(String[] args) {
		Trigger trigger1 = PromotionBuilder.when(WebsiteWhen.LEVEL).setParam(TriggerParams.LEVEL_THRESHOLD, 2).build();
		Trigger trigger2 = PromotionBuilder.when(HotelWhen.BIRTHDAY).build();
		Applier applier = PromotionBuilder.how(How.PERCENT_OFF).setParam(ApplierParams.PERCENT, 0.2).build();

		Promotion pro1 = new PromotionBuilder().setApplier(applier).setTrigger(trigger1).build();
		Promotion pro2 = new PromotionBuilder().setApplier(applier).setTrigger(trigger2).build();

		PromotionPo propo1 = new PromotionPoBuilder(new PromotionVoBuilder(Scope.Website, pro1).getPromotionInfo())
				.getPromotionInfo();
		PromotionPo propo2 = new PromotionPoBuilder(
				new PromotionVoBuilder(Scope.Hotel, pro2).setHotelId(1).getPromotionInfo()).getPromotionInfo();

		OrderPo pp = new OrderPoBuilder().setOrderId("12345").getOrderInfo();
		Set<PromotionPo> proSet =

				execute(session -> {
					// session.save(propo1);
					// session.save(propo2);
					// proSet.add(propo1);
					// proSet.add(propo2);
					// po.setPromotionsInfo(proSet);
					// session.save(po);
					PromotionPo po = session.get(HotelPromotionPo.class, 4L);
					PromotionPo po2 = session.get(WebsitePromotionPo.class, 9L);

					HashSet<PromotionPo> tmp = new HashSet<>();

					tmp.add(po);
					tmp.add(po2);

					return tmp;
				});

		proSet = proSet.stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.map(vo -> new PromotionPoBuilder(vo).getPromotionInfo()).collect(Collectors.toSet());
		pp.setPromotionsInfo(proSet);

		execute(session -> {
			session.save(pp);
			return true;
		});
	}
}
