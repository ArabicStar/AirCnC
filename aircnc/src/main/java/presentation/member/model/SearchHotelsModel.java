package presentation.member.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.hotel.HotelVo;
import vo.member.MemberVo;

/**
 * the model of hotel searched
 * aiming to wrap the HotelVo into the presentation manager.
 * @author paranoia
 *
 */
public class SearchHotelsModel {
	
	private final IntegerProperty star;
	
	private final StringProperty hotelName;
	private final DoubleProperty hotelGrade;
	private final StringProperty hotelScope;
	private final StringProperty hotelLocation;
	private final DoubleProperty lowestPrice;
	private final BooleanProperty reserve;
	private final ObjectProperty<MemberVo> historyOrder;
	
	private final IntegerProperty execute;
	private final IntegerProperty unexecute;
	private final IntegerProperty abnormal;
	private final IntegerProperty repeal;
	
	private final StringProperty id;
	private final StringProperty intro;
	private final StringProperty equip;
	private final StringProperty roomName;
	private final StringProperty roomPrice;
		
	/**
     * Default constructor.
     */
    public SearchHotelsModel() {
        this(null);
    }
    
	//姑且没有PromotionVo
	//private final ObjectProperty<PromotionVo> promotion;
    /**
     * Constructor with some initial data.
     * 
     * @param hotelName
     * @param hotelGrade
     * @param htoelScope
     * @param hotelLocation
     * @param lowestPrice
     * @param reverse
     * @param historyOrder
     * @param execute
     * @param unexecute
     * @param abnormal
     * @param repeal
     */
	public SearchHotelsModel(HotelVo vo){
		
		this.star = new SimpleIntegerProperty(vo.getStar());
		this.hotelName = new SimpleStringProperty(vo.getName());
		this.hotelGrade = new SimpleDoubleProperty(vo.getGrade());
		this.hotelScope = new SimpleStringProperty(vo.getScope());
		this.hotelLocation = new SimpleStringProperty(vo.getLocation());
		
		//这条不太对，等待修改
		this.lowestPrice = new SimpleDoubleProperty(vo.getLowestPrice());
		
		this.reserve = new SimpleBooleanProperty(true);
		
		this.historyOrder = new SimpleObjectProperty<MemberVo>();
		
		this.execute = new SimpleIntegerProperty(0);
		this.unexecute = new SimpleIntegerProperty(1);
		this.abnormal = new SimpleIntegerProperty(0);
		this.repeal = new SimpleIntegerProperty(0);
		
		this.id = new SimpleStringProperty(Integer.toString(vo.getId()));
		this.intro = new SimpleStringProperty(vo.getIntroduction());
		this.equip = new SimpleStringProperty(vo.getEquipment());
		this.roomPrice = new SimpleStringProperty(vo.getStringRoomPrice());
		this.roomName = new SimpleStringProperty(vo.getStringRoomName());
		//this.promotion = new SimpleObjectProperty<HotelPromotionCell>();
		
	}
	
	public int getStar() {
        return star.get();
    }

    public void setStar(int i) {
        this.star.set(i);
    }

    public IntegerProperty starProperty() {
        return star;
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
    
    public double getHotelGrade() {
        return hotelGrade.get();
    }

    public void setHotelGrade(int newGrade) {
        this.hotelGrade.set(newGrade);
    }

    public DoubleProperty hotelGradeProperty() {
        return hotelGrade;
    }
    
    public String getHotelScope() {
        return hotelScope.get();
    }

    public void setHotelScope(String newScope) {
        this.hotelScope.set(newScope);
    }

    public StringProperty hotelScopeProperty() {
        return hotelScope;
    }
    
    public String getHotelLocation() {
        return hotelLocation.get();
    }

    public void setHotelLocation(String newLocation) {
        this.hotelLocation.set(newLocation);
    }

    public StringProperty hotelLocationProperty() {
        return hotelLocation;
    }
    
    public double getLowestPrice() {
        return lowestPrice.get();
    }

    public void setLowestPrice(int newLow) {
        this.lowestPrice.set(newLow);
    }

    public DoubleProperty lowestPriceProperty() {
        return lowestPrice;
    }
    
    public boolean getReserve() {
        return reserve.get();
    }

    public void setReserve(boolean valid) {
        this.reserve.set(valid);
    }

    public BooleanProperty reserveProperty() {
        return reserve;
    }
    
    public MemberVo getHistoryOrder() {
        return historyOrder.get();
    }

    public void setUsername(MemberVo newVo) {
        this.historyOrder.set(newVo);
    }

    public ObjectProperty<MemberVo> historyOrderProperty() {
        return historyOrder;
    }
    
    public int getExecuteOrderNum() {
        return execute.get();
    }

    public void setExecuteOrderNum(int i) {
        this.execute.set(i);
    }

    public IntegerProperty executeOrderProperty() {
        return execute;
    }
    
    public int getUnexecuteOrderNum() {
        return unexecute.get();
    }

    public void setUnexecuteOrderNum(int i) {
        this.unexecute.set(i);
    }

    public IntegerProperty unexecuteOrderProperty() {
        return unexecute;
    }
    
    public int getAbnormalOrderNum() {
        return abnormal.get();
    }

    public void setAbnormalOrderNum(int i) {
        this.abnormal.set(i);
    }

    public IntegerProperty abnormalOrderProperty() {
        return abnormal;
    }
    
    public int getRepealOrderNum() {
        return repeal.get();
    }

    public void setRepealOrderNum(int i) {
        this.repeal.set(i);
    }

    public IntegerProperty repealOrderProperty() {
        return repeal;
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
    
    public String getIntro() {
        return intro.get();
    }

    public void setIntro(String newIntro) {
        this.intro.set(newIntro);
    }

    public StringProperty introProperty() {
        return intro;
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
    
    public String getRoomPrice() {
        return roomPrice.get();
    }

    public void setRoomPrice(String newName) {
        this.roomPrice.set(newName);
    }

    public StringProperty roomPriceProperty() {
        return roomPrice;
    }
    
    public String getRoomName() {
        return roomName.get();
    }

    public void setRoomName(String newName) {
        this.roomName.set(newName);
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }
}
