package utils.promotion;

public abstract class WebsiteTrigger extends Trigger {
	public enum When {
		PEROID, LEVEL, TRADE_AREA
	}

	protected When when;

	public WebsiteTrigger(When when) {
		this.when = when;
	}
}
