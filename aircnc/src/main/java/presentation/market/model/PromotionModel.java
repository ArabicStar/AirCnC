package presentation.market.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PromotionModel {
	private StringProperty promotionDescription;
	
	public PromotionModel() {
		
	}
	
	public PromotionModel(String promotionDescription) {
		this.promotionDescription = new SimpleStringProperty(promotionDescription);
	}

	public StringProperty getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(StringProperty promotionDescription) {
		this.promotionDescription = promotionDescription;
	}


	

}
