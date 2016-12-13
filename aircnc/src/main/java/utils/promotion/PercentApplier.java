package utils.promotion;

import utils.info.order.OrderInfo;
import vo.order.OrderVoBuilder;

public class PercentApplier extends Applier {

	private double discount;

	public PercentApplier(double discount) {
		super(How.PERCENT);
		this.discount = discount;
	}

	@Override
	public OrderInfo applyTo(OrderInfo info) {
		double discountPrice = info.getDiscountPrice() - (1.0 - discount) * info.getOriginalPrice();
		return new OrderVoBuilder(info).setDiscountPrice(discountPrice).getOrderInfo();
	}

	@Override
	public String how() {
		return String.format(how.getFormatString(), discount * 100.0);
	}

}
