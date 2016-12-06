package presentation.manage.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.hotel.HotelVo;

/**
 * the model of hotel
 * aiming to wrap the HotelVo into the presentation manager.
 * @author paranoia
 *
 */
public class HotelManageModel {
	
	private final StringProperty hotelName;
	private final StringProperty id;
	
	/**
	 * Default constructor.
	 */
	public HotelManageModel() {
		this(null);
	}
	
	public HotelManageModel(HotelVo vo){
		this.id = new SimpleStringProperty(String.valueOf(vo.getId()));
		this.hotelName = new SimpleStringProperty(vo.getName());		
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
	
	public String getHotelName() {
        return hotelName.get();
    }

    public void setHotelName(String newName) {
        this.hotelName.set(newName);
    }

    public StringProperty hotelNameProperty() {
        return hotelName;
    }
    
}