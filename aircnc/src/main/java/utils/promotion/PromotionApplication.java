package utils.promotion;

import utils.info.order.OrderInfo;

public final class PromotionApplication {
	public static OrderInfo apply(OrderInfo order, final Promotion<?> prom) {
		if (prom.canApplyTo(order))
			return prom.applyTo(order);
		return order;
	}

	private PromotionApplication() {
	}
}
