package utils.info.promotion;

public abstract class PromotionInfo extends PromotionInfoTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1594812134845326912L;
	protected boolean isActive;
	protected boolean isValid;

	protected PromotionInfo() {
		this.id = 0;
		this.name = BLANK;
		this.isActive = true;
		this.isPractical = true;
		this.isValid = true;
	}

	public PromotionInfo(Scope scope) {
		this();
		this.scope = scope;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return scope
	 */
	public Scope getScope() {
		return scope;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return isActive
	 */
	public boolean getActive() {
		return isActive;
	}

	/**
	 * @return isPractical
	 */
	public boolean getPractical() {
		return isPractical;
	}

	public boolean isValid() {
		return isValid;
	}

	public void expire() {
		this.isActive = false;
	}

	public abstract int getHotelId();

	public abstract String getContentString();
}
