package presentation.market.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import presentation.market.utils.ButtonName;

public class PromotionModel {
	private final StringProperty promotionDescription;
	
	private final ObjectProperty<ButtonName> operation;



	public PromotionModel(String promotionDescription) {
		this.promotionDescription = new SimpleStringProperty(promotionDescription);
		this.operation = new SimpleObjectProperty<ButtonName>();
	}

	public StringProperty getPromotionDescription() {
		return promotionDescription;
	}
	
    public ButtonName getOperation() {
        return operation.get();
    }

    public void setOperation(ButtonName name) {
        this.operation.set(name);
    }

    public ObjectProperty<ButtonName> operationProperty() {
        return operation;
    }


}
