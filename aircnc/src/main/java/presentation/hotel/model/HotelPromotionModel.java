package presentation.hotel.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.promotion.HotelPromotionVo;

public class HotelPromotionModel {
	private final StringProperty description;
    private final ObjectProperty<HotelPromotionVo> operation;
    
    /**
     * Default constructor.
     */
    public HotelPromotionModel() {
        this(null);
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param description
     * @param operation
     */
    public HotelPromotionModel(HotelPromotionVo vo) {
    	this.description = new SimpleStringProperty(vo.description());
    	this.operation = new SimpleObjectProperty<HotelPromotionVo>(vo);
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
    
    public HotelPromotionVo getOperation() {
        return operation.get();
    }

    public void setOperation(HotelPromotionVo vo) {
        this.operation.set(vo);
    }

    public ObjectProperty<HotelPromotionVo> operationProperty() {
        return operation;
    }

}
