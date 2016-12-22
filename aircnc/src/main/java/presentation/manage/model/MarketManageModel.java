package presentation.manage.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private final ObjectProperty<MarketVo> operation;
	
	private MarketVo vo;
	
	/**
	 * Default constructor.
	 */
	public MarketManageModel() {
		this(null);
	}
	
	public MarketManageModel(MarketVo vo){
		this.vo = vo;
		this.id = new SimpleStringProperty(vo.getId());
		this.marketName = new SimpleStringProperty(vo.getName());
		this.operation = new SimpleObjectProperty<MarketVo>(vo);
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
    
    public MarketVo getOperation() {
        return operation.get();
    }

    public void setOperation(MarketVo vo) {
        this.operation.set(vo);
    }

    public ObjectProperty<MarketVo> operationProperty() {
        return operation;
    }
    
    public MarketVo getMarketVo(){
    	return vo;
    }
}
