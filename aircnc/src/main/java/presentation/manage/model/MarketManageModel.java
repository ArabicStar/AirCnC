package presentation.manage.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.market.MarketVo;

/**
 * the model of market
 * aiming to wrap the MarketVo into the presentation manager.
 * @author paranoia
 *
 */
public class MarketManageModel {
	
	private final StringProperty marketName;
	private final StringProperty id;
	
	/**
	 * Default constructor.
	 */
	public MarketManageModel() {
		this(null);
	}
	
	public MarketManageModel(MarketVo vo){
		this.id = new SimpleStringProperty(vo.getId());
		this.marketName = new SimpleStringProperty(vo.getName());		
	}
	
	public String getID() {
        return id.get();
    }

    public void setId(String newId) {
        this.id.set(newId);
    }

    public StringProperty idProperty() {
        return id;
    }
	
	public String getMarketName() {
        return marketName.get();
    }

    public void setMarketName(String newName) {
        this.marketName.set(newName);
    }

    public StringProperty marketNameProperty() {
        return marketName;
    }
}
