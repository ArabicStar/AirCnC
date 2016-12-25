package utils.promotion.applier;

import java.io.Serializable;

public enum ApplierParams implements Serializable{
	PERCENT, AMOUNT;
	
	public String paramName() {
		return this.name().toLowerCase();
	}
}
