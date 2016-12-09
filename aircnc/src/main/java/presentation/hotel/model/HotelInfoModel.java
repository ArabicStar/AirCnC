package presentation.hotel.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.hotel.HotelVo;

public class HotelInfoModel {
	private final StringProperty id;
	private final StringProperty name;
	private final StringProperty location;
	private final StringProperty scope;
	private final StringProperty Stringro;
	private final StringProperty equip;
	private final StringProperty star;
	private final StringProperty roomName;
	private final StringProperty roomPrice;
	private final StringProperty grade;
	
	public HotelInfoModel(){
		this(null);
	}
	
	public HotelInfoModel(HotelVo vo){
		this.name = new SimpleStringProperty(vo.getName());
		this.equip = new SimpleStringProperty(vo.getEquipment());
		this.location = new SimpleStringProperty(vo.getLocation());
		this.scope = new SimpleStringProperty(vo.getScope());
		this.Stringro = new SimpleStringProperty(vo.getIntroduction());
		this.id = new SimpleStringProperty(Integer.toString(vo.getId()));
		this.star = new SimpleStringProperty(Integer.toString(vo.getStar()));
		this.grade = new SimpleStringProperty(Double.toString(vo.getGrade()));
		this.roomPrice = new SimpleStringProperty(vo.getStringRoomPrice());
		this.roomName = new SimpleStringProperty(vo.getStringRoomName());
	}
	
	public String getId() {
        return id.get();
    }

    public void setId(String newId) {
        this.id.set(newId);
    }

    public StringProperty idProperty() {
        return id;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        this.name.set(newName);
    }

    public StringProperty nameProperty() {
        return name;
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
        return Stringro.get();
    }

    public void setIntro(String newIntro) {
        this.Stringro.set(newIntro);
    }

    public StringProperty StringroProperty() {
        return Stringro;
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
