package presentation.manage.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

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
	
	private final ObjectProperty<List<String>> promotion;
	private final ObjectProperty<List<ManageCommentModel>> comments;
	
	private HotelVo vo;
	
	/**
	 * Default constructor.
	 */
	public HotelManageModel() {
		this(null);
	}
	
	public HotelManageModel(HotelVo vo){
		this.vo = vo;
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
		
		Set<PromotionVo> promotionsList = vo.getPromotions();
		List<String> proGeneral = new ArrayList<String>();
		List<String> pro = new ArrayList<String>();
		Iterator<PromotionVo> iter3 = promotionsList.iterator();
		while(iter3.hasNext()){
			proGeneral.add(iter3.next().getContentString());
			pro.add(iter3.next().getDescription());
		}
		
		this.promotion = new SimpleObjectProperty<List<String>>(proGeneral);
		
		List<CommentVo> commentsList = vo.getComments();
		List<ManageCommentModel> commentModels = new ArrayList<ManageCommentModel>();
		Iterator<CommentVo> iter2 = commentsList.iterator();
		while(iter2.hasNext()){
			commentModels.add(new ManageCommentModel(iter2.next()));
		}
		this.comments = new SimpleObjectProperty<List<ManageCommentModel>>(commentModels);
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
    
    public HotelVo getHotelVo(){
    	return vo;
    }
    
    public void setPromotion(List<String> newPro) {
        this.promotion.set(newPro);
    }

    public ObjectProperty<List<String>> promotionProperty() {
        return promotion;
    }
    
    public List<String> getPromotion(){
    	return this.promotion.get();
    }
    
    public void setComments(List<ManageCommentModel> newComment) {
        this.comments.set(newComment);
    }

    public ObjectProperty<List<ManageCommentModel>> commentProperty() {
        return comments;
    }
    
    public List<ManageCommentModel> getComments(){
    	return this.comments.get();
    }
}
