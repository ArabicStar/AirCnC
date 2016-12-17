package utils.info.promotion;

public abstract class PromotionInfoTemplate {
	protected static String BLANK = "";

	public enum Scope {
		Website, Hotel;
	}

	protected long id;
	protected Scope scope;
	protected String name;
	protected boolean isActive;
	protected boolean isPractical;
	protected int hotelId;

	public static boolean checkId(long id) {
		return id >= 0;
	}

	public static boolean checkName(String name) {
		return name != null && name.length() > 0;
	}
}
