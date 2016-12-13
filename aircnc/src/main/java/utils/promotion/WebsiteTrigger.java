package utils.promotion;

public abstract class WebsiteTrigger extends Trigger {
	public enum When {

	}

	public WebsiteTrigger(When when) {
		super(Where.WEBSITE);
	}
}
