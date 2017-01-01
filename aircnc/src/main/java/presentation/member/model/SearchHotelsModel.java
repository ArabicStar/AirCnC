package presentation.member.model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import interactor.impl.member.MemberInfoCourier;
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
import presentation.member.manager.impl.MyOrderManagerImpl;
import vo.hotel.HotelVo;

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
	private final ObjectProperty<List<MyOrderModel>> historyOrder;
	
	private final IntegerProperty execute;
	private final IntegerProperty unexecute;
	private final IntegerProperty abnormal;
	private final IntegerProperty repeal;
	
	private final StringProperty id;
	private final StringProperty intro;
	private final StringProperty equip;
	private final StringProperty roomName;
	private final StringProperty roomPrice;
	
	private final ObjectProperty<String[]> promotion;
	
	private final HotelVo hotel;
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
		this.hotel = vo;
		
		this.star = new SimpleIntegerProperty(vo.getStar());
		this.hotelName = new SimpleStringProperty(vo.getName());
		this.hotelGrade = new SimpleDoubleProperty(vo.getGrade());
		this.hotelScope = new SimpleStringProperty(vo.getScope());
		this.hotelLocation = new SimpleStringProperty(vo.getLocation());
		//这条不太对，等待修改
		this.lowestPrice = new SimpleDoubleProperty(vo.getLowestPrice());
		
		this.reserve = new SimpleBooleanProperty(true);
		
		MemberInfoCourier.getInstance().getMemberAllOrders();
		List<MyOrderModel> bufferedList = MyOrderManagerImpl.getInstance().getOrderList();
		this.historyOrder = new SimpleObjectProperty<List<MyOrderModel>>(
				bufferedList.stream().filter(order->order.getHotelName().equals(vo.getName())).collect(Collectors.toList()));
		
		int executeNum = 0; int unexecuteNum = 0; int abnormalNum = 0;  int repealNum = 0;
		Iterator<MyOrderModel> iter = historyOrder.getValue().iterator();
		while(iter.hasNext()){
			switch(iter.next().getState()){
			case "异常":
				abnormalNum++;  break;
	        case "已执行": 
	        	executeNum++;  break;
	        case "未执行": 
	        	unexecuteNum++;  break;
	        case "撤销": 
	        	repealNum++;  break;
	        case "已评价": 
	        	executeNum++;  break;
	        case "申诉中": 
	        	unexecuteNum++;  break;
			}
		}
		this.execute = new SimpleIntegerProperty(executeNum);
		this.unexecute = new SimpleIntegerProperty(unexecuteNum);
		this.abnormal = new SimpleIntegerProperty(abnormalNum);
		this.repeal = new SimpleIntegerProperty(repealNum);
		
		this.id = new SimpleStringProperty(Integer.toString(vo.getId()));
		this.intro = new SimpleStringProperty(vo.getIntroduction());
		this.equip = new SimpleStringProperty(vo.getEquipment());
		this.roomPrice = new SimpleStringProperty(vo.getStringRoomPrice());
		this.roomName = new SimpleStringProperty(vo.getStringRoomName());
		
		this.promotion = new SimpleObjectProperty<String[]>();
		
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
    
    public List<MyOrderModel> getHistoryOrder() {
        return historyOrder.get();
    }

    public void setUsername(List<MyOrderModel> newVo) {
        this.historyOrder.set(newVo);
    }

    public ObjectProperty<List<MyOrderModel>> historyOrderProperty() {
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
    
    public HotelVo getHotel(){
    	return this.hotel;
    }
}
