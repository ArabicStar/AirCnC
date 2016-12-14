package utils.promotion.applier;

public enum ApplierParams {
	PERCENT, AMOUNT;
	
	public String paramName() {
		return this.name().toLowerCase();
	}
}
