package utils.promotion;

import utils.info.order.OrderInfo;

public class HotelMultiTrigger extends HotelTrigger {

	private int numThershold;

	public HotelMultiTrigger(final int numThershold) {
		super(When.MULTI);
	}

	@Override
	public boolean test(OrderInfo order) {
		return order.getRoomNumber() >= numThershold;
	}

	@Override
	public String when() {
		return new StringBuilder("预订").append(numThershold).append("间及以上").toString();
	}

}
