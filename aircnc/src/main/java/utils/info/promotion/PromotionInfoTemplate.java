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
		return id >= 0L;
	}

	public static boolean checkName(String name) {
		return name != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionInfoTemplate other = (PromotionInfoTemplate) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
