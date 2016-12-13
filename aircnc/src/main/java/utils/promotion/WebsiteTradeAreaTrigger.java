package utils.promotion;

public abstract class WebsiteTradeAreaTrigger extends WebsiteTrigger {
	private String targetTradeArea;

	public WebsiteTradeAreaTrigger(String targetTradeArea) {
		super(When.TRADE_AREA);
		this.targetTradeArea = targetTradeArea;
	}

	public String when() {
		return new StringBuilder("于").append(targetTradeArea).toString();
	}
}
