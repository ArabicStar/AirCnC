package utils.promotion;

import utils.info.order.OrderInfo;
import vo.order.OrderVoBuilder;

public class ConstApplier extends Applier {
	private int off;

	public ConstApplier(int off) {
		super(How.CONST);
		this.off = off;
	}

	@Override
	public OrderInfo applyTo(OrderInfo info) {
		double discountPrice = info.getDiscountPrice() - off;
		return new OrderVoBuilder(info).setDiscountPrice(discountPrice).getOrderInfo();
	}

	@Override
	public String how() {
		return String.format(how.getFormatString(), off);
	}

}
