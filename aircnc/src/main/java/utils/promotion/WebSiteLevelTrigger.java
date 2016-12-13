package utils.promotion;

public abstract class WebSiteLevelTrigger extends WebsiteTrigger {
	private int levelTreshold;

	public WebSiteLevelTrigger(final int levelTreshold) {
		super(When.LEVEL);
		this.levelTreshold = levelTreshold;
	}

	@Override
	public String when() {
		return new StringBuilder(levelTreshold).append("级会员").toString();
	}

}
