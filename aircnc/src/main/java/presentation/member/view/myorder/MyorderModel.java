package presentation.member.view.myorder;

import java.time.LocalDateTime;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import presentation.member.view.myorder.utils.FunctionButtons;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import vo.order.OrderVo;

/**
 * Model class for an order.
 *
 * @author paranoia
 */
public class MyorderModel {

    private final StringProperty hotelName;
    private final StringProperty checkinTime;
    private final StringProperty state;
    private final StringProperty timeAndSum;
    private final StringProperty totalPrice;
    private final ObjectProperty<Button[]> operation;

    /**
     * Default constructor.
     */
    public MyorderModel() {
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
    public MyorderModel(String s1,String s2,String s3,String s4,String s5,Button[] buttons) {
        this.hotelName = new SimpleStringProperty(s1);
        
        //process the checkinTime
        this.checkinTime = new SimpleStringProperty(s2);
        
        this.state = new SimpleStringProperty(s3);
        this.timeAndSum = new SimpleStringProperty(s4);
        this.totalPrice = new SimpleStringProperty(s5);
        this.operation = new SimpleObjectProperty<Button[]>
		(new Button[]{new FunctionButtons("查看",false),new FunctionButtons("申诉",true)});
        
    }
    
    public MyorderModel(OrderVo order) {
        this.hotelName = new SimpleStringProperty(order.getHotelName());
        
        //process the checkinTime
        this.checkinTime = new SimpleStringProperty(transformTime(order.getEntryTime()));
        
        this.state = new SimpleStringProperty(String.valueOf(order.getStatus()));
        this.timeAndSum = new SimpleStringProperty(order.getStayDays()+"晚/"+order.getRoomNumber()+"间");
        this.totalPrice = new SimpleStringProperty(String.valueOf(order.getPrice())+"元");
        switch(order.getStatus()){
        case UNEXECUTED : 
        	this.operation = new SimpleObjectProperty<Button[]>
        			(new Button[]{new FunctionButtons("查看",false),new FunctionButtons("撤销",true)});
        	break;
        case EXECUTED : 
        	this.operation = new SimpleObjectProperty<Button[]>
					(new Button[]{new FunctionButtons("查看",false),new FunctionButtons("评价",false)});
        	break;
        case ABNORMAL : 
        	this.operation = new SimpleObjectProperty<Button[]>
					(new Button[]{new FunctionButtons("查看",false),new FunctionButtons("申诉",true)});
        	break;
        case REPEALED :
        	this.operation = new SimpleObjectProperty<Button[]>
					(new Button[]{new FunctionButtons("查看",false)});
        	break;
        default : 
        	this.operation = new SimpleObjectProperty<Button[]>(null);
        	break;
        }
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
    
    public Button[] getOperation() {
        return operation.get();
    }

    public void setOperation(Button[] newButtons) {
        this.operation.set(newButtons);
    }

    public ObjectProperty<Button[]> operationProperty() {
        return operation;
    }
    

}
