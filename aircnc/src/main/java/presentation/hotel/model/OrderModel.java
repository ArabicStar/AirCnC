package presentation.hotel.model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.order.OrderVo;

public class OrderModel {
	private final StringProperty username;
	private final StringProperty userID;
	private final StringProperty orderID;
    private final StringProperty hotelName;
    private final StringProperty checkinTime;
    private final StringProperty leaveTime;
    private final StringProperty state;
    private final IntegerProperty roomNum;
    private final StringProperty roomType;
    private final IntegerProperty peopleNum;
    private final StringProperty hasChild;
    private final StringProperty timeAndSum;
    private final StringProperty totalPrice;
    private final ObjectProperty<OrderVo> operation;

    /**
     * Default constructor.
     */
    public OrderModel() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param hotelName
     * @param checkinTime
     * @param state
     * @param timeAndSum
     * @param totalPrice
     * @param operation
     */
    public OrderModel(OrderVo order) {
    	this.username = new SimpleStringProperty(order.getMember().getName());
    	this.userID = new SimpleStringProperty(order.getMember().getId());
    	this.orderID = new SimpleStringProperty(order.getOrderId());
        this.hotelName = new SimpleStringProperty(order.getHotel().getName());
        
        //process the checkinTime
        this.checkinTime = new SimpleStringProperty(transformTime(order.getEntryTime()));
        
        this.roomNum = new SimpleIntegerProperty(order.getRoomNumber());
        this.leaveTime = new SimpleStringProperty(transformTime(order.getEntryTime().plusDays(order.getStayDays())));
        this.roomType = new SimpleStringProperty(order.getRoomType());
        this.peopleNum = new SimpleIntegerProperty(order.getPeopleNumber());
        this.hasChild = new SimpleStringProperty(order.getHasChildren() == true? "有":"无");
        
        String state;
        switch(order.getStatus()){
        case ABNORMAL: 
        	state = "异常";  break;
        case EXECUTED: 
        	state = "已执行";  break;
        case UNEXECUTED: 
        	state = "未执行";  break;
        case REPEALED: 
        	state = "撤销";  break;
        case REVIEWED: 
        	state = "已评价";  break;
        case APPEALING: 
        	state = "申诉中";  break;
        default: 
        	state = "";  break;
        }
        
        this.state = new SimpleStringProperty(state);
        this.timeAndSum = new SimpleStringProperty(order.getStayDays()+"晚/"+order.getRoomNumber()+"间");
        this.totalPrice = new SimpleStringProperty(String.valueOf(order.getOriginalPrice())+"元");
        this.operation = new SimpleObjectProperty<OrderVo>(order);
        
        
    }
    
    /**
     * transform the local date time (yyyy/mm/dd HH:mm)
     *  to the new format (yyyy-mm-dd)
     * @param date
     * @return new date format(String)
     */
    
    private static String transformTime(LocalDateTime date){
    	
    	String result = date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();
    	
		return result;	
    }

    public String getHotelName() {
        return hotelName.get();
    }

    public void setHotelName(String firstName) {
        this.hotelName.set(firstName);
    }

    public StringProperty hotelNameProperty() {
        return hotelName;
    }
    
    public String getCheckInTime() {
        return checkinTime.get();
    }

    public void setCheckInTime(LocalDateTime date) {
        this.checkinTime.set(transformTime(date));
    }

    public StringProperty checkInTimeProperty() {
        return checkinTime;
    }
    
    public String getState() {
        return state.get();
    }

    public void setState(String newState) {
        this.state.set(newState);
    }

    public StringProperty stateProperty() {
        return state;
    }

    public String getTimeAndSum() {
        return timeAndSum.get();
    }

    public void setTimeAndSum(String newTimeAndSum) {
        this.timeAndSum.set(newTimeAndSum);
    }

    public StringProperty timeAndSumProperty() {
        return timeAndSum;
    }
    
    public String getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(String newPrice) {
        this.totalPrice.set(newPrice);
    }

    public StringProperty totalPriceProperty() {
        return totalPrice;
    }
    
    public OrderVo getOperation() {
        return operation.get();
    }

    public void setOperation(OrderVo vo) {
        this.operation.set(vo);
    }

    public ObjectProperty<OrderVo> operationProperty() {
        return operation;
    }
    
    public String getUserName() {
        return username.get();
    }

    public void setUserName(String newName) {
        this.username.set(newName);
    }

    public StringProperty usernameProperty() {
        return username;
    }
    
    public String getOrderID() {
        return orderID.get();
    }

    public void setorderID(String newID) {
        this.orderID.set(newID);
    }

    public StringProperty orderIDProperty() {
        return orderID;
    }
    
    public String getuserID() {
        return userID.get();
    }

    public void setuserID(String newID) {
        this.userID.set(newID);
    }

    public StringProperty userIDProperty() {
        return userID;
    }
    
    public String getLeaveTime() {
        return leaveTime.get();
    }

    public void setLeaveTime(String newTime) {
        this.leaveTime.set(newTime);
    }

    public StringProperty leaveTimeProperty() {
        return leaveTime;
    }
    
    public String getRoomType() {
        return roomType.get();
    }

    public void setRoomType(String newType) {
        this.roomType.set(newType);
    }

    public StringProperty roomTypeProperty() {
        return roomType;
    }
    
    public int getRoomNumber() {
        return roomNum.get();
    }

    public void setRoomNumber(int num) {
        this.roomNum.set(num);
    }

    public IntegerProperty roomNumberProperty() {
        return roomNum;
    }
    
    public int getPeopleNumber() {
        return peopleNum.get();
    }

    public void setPeopleNum(int num) {
        this.peopleNum.set(num);
    }

    public IntegerProperty peopleNumProperty() {
        return peopleNum;
    }
    
    public String hasChild() {
        return hasChild.get();
    }

    public void setHasChild(String hasChild) {
        this.hasChild.set(hasChild);
    }

    public StringProperty hasChildProperty() {
        return hasChild;
    }
}
