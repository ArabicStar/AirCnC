package presentation.market.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.promotion.PromotionVo;

public class WebsitePromotionModel {
	private final StringProperty description;
    private final ObjectProperty<PromotionVo> operation;
    
    /**
     * Default constructor.
     */
    public WebsitePromotionModel() {
        this(null);
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param description
     * @param operation
     */
    public WebsitePromotionModel(PromotionVo vo) {
    	this.description = new SimpleStringProperty(vo.description());
    	this.operation = new SimpleObjectProperty<PromotionVo>(vo);
    }
    
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String firstName) {
        this.description.set(firstName);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    
    public PromotionVo getOperation() {
        return operation.get();
    }

    public void setOperation(PromotionVo vo) {
        this.operation.set(vo);
    }

    public ObjectProperty<PromotionVo> operationProperty() {
        return operation;
    }


}
