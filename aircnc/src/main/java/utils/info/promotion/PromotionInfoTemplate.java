package utils.info.promotion;

import java.io.Serializable;

public abstract class PromotionInfoTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2832349554224418817L;
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
