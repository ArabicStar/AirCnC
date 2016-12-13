package utils.promotion;

import utils.info.order.OrderInfo;

public abstract class Applier {

	public enum How {
		PERCENT("打%d折"), CONST("减%d元");

		private String formatString;

		private How(String formatString) {
			this.formatString = formatString;
		}

		public String getFormatString() {
			return formatString;
		}
	}

	protected How how;

	protected Applier(How how) {
		this.how = how;
	}

	public abstract OrderInfo applyTo(OrderInfo Info);

	public abstract String how();

}
