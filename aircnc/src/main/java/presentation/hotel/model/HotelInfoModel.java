package presentation.hotel.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.hotel.HotelVo;

public class HotelInfoModel {
	private final IntegerProperty id;
	private final StringProperty location;
	private final StringProperty scope;
	private final StringProperty intro;
	private final StringProperty equip;
	private final IntegerProperty star;
	private final StringProperty roomName;
	private final StringProperty roomPrice;
	private final DoubleProperty grade;
	
	public HotelInfoModel(){
		this(null);
	}
	
	public HotelInfoModel(HotelVo vo){
		this.equip = new SimpleStringProperty(vo.getEquipment());
		this.location = new SimpleStringProperty(vo.getLocation());
		this.scope = new SimpleStringProperty(vo.getScope());
		this.intro = new SimpleStringProperty(vo.getIntroduction());
		this.id = new SimpleIntegerProperty(vo.getId());
		this.star = new SimpleIntegerProperty(vo.getStar());
		this.grade = new SimpleDoubleProperty(vo.getGrade());
		this.roomPrice = new SimpleStringProperty(vo.getStringRoomPrice());
		this.roomName = new SimpleStringProperty(vo.getStringRoomName());
	}
}
