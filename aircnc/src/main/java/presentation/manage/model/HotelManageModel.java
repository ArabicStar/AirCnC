package presentation.manage.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private final ObjectProperty<HotelVo> operation;
	
	private final StringProperty location;
	private final StringProperty scope;
	private final StringProperty intro;
	private final StringProperty equip;
	private final StringProperty star;
	private final StringProperty roomName;
	private final StringProperty roomPrice;
	private final StringProperty grade;
	
	/**
	 * Default constructor.
	 */
	public HotelManageModel() {
		this(null);
	}
	
	public HotelManageModel(HotelVo vo){
		this.id = new SimpleStringProperty(String.valueOf(vo.getId()));
		this.hotelName = new SimpleStringProperty(vo.getName());	
		this.operation = new SimpleObjectProperty<HotelVo>(vo);
		this.equip = new SimpleStringProperty(vo.getEquipment());
		this.location = new SimpleStringProperty(vo.getLocation());
		this.scope = new SimpleStringProperty(vo.getScope());
		this.intro = new SimpleStringProperty(vo.getIntroduction());
		this.star = new SimpleStringProperty(Integer.toString(vo.getStar()));
		this.grade = new SimpleStringProperty(Double.toString(vo.getGrade()));
		this.roomPrice = new SimpleStringProperty(vo.getStringRoomPrice());
		this.roomName = new SimpleStringProperty(vo.getStringRoomName());
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
    
    public HotelVo getOperation() {
        return operation.get();
    }

    public void setOperation(HotelVo vo) {
        this.operation.set(vo);
    }

    public ObjectProperty<HotelVo> operationProperty() {
        return operation;
    }
    
    public String getEquip() {
        return equip.get();
    }

    public void setEquip(String newEquip) {
        this.equip.set(newEquip);
    }

    public StringProperty equipProperty() {
        return equip;
    }
    
    public String getIntro() {
        return intro.get();
    }

    public void setIntro(String newIntro) {
        this.intro.set(newIntro);
    }

    public StringProperty StringroProperty() {
        return intro;
    }
    
    public String getLocation() {
        return location.get();
    }

    public void setLocation(String newLocation) {
        this.location.set(newLocation);
    }

    public StringProperty locationProperty() {
        return location;
    }
    
    public String getScope() {
        return scope.get();
    }

    public void setScope(String newScope) {
        this.scope.set(newScope);
    }

    public StringProperty scopeProperty() {
        return scope;
    }
    
    public String getRoomName() {
        return roomName.get();
    }

    public void setRoomName(String newRoomName) {
        this.roomName.set(newRoomName);
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }
    
    public String getRoomPrice() {
        return roomPrice.get();
    }

    public void setRoomPrice(String newRoomPrice) {
        this.roomPrice.set(newRoomPrice);
    }

    public StringProperty roomPriceProperty() {
        return roomPrice;
    }
    
    public String getStar() {
        return star.get();
    }

    public void setStar(String newStar) {
        this.star.set(newStar);
    }

    public StringProperty starProperty() {
        return star;
    }
    
    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String newGrade) {
        this.grade.set(newGrade);
    }

    public StringProperty gradeProperty() {
        return grade;
    }
}
