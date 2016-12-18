package presentation.market.model;

import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class MyOrderModel {

    private final StringProperty hotelName;
    private final StringProperty checkinTime;
    private final StringProperty state;
    private final StringProperty timeAndSum;
    private final StringProperty totalPrice;
    private final ObjectProperty<OrderStatus> operation;
    private final BooleanProperty isReviewed;
	private final int stayDays;
	private LocalDateTime leaveTime;
	private OrderVo orderVo;
	private final BooleanProperty hasChildren;


	/**
     * Default constructor.
     */
    public MyOrderModel() {
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
    public MyOrderModel(OrderVo order) {
    	this.orderVo = order;
        this.hotelName = new SimpleStringProperty(order.getHotelName());
        this.stayDays = order.getStayDays();
        this.leaveTime = order.getEntryTime().plusDays(stayDays);
        this.isReviewed = new SimpleBooleanProperty(order.getReviewed());
        //process the checkinTime
        this.checkinTime = new SimpleStringProperty(transformTime(order.getEntryTime()));
        this.hasChildren = new SimpleBooleanProperty(order.getHasChildren());
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
        this.operation = new SimpleObjectProperty<OrderStatus>(order.getStatus());
        
        
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
    
    public OrderStatus getOperation() {
        return operation.get();
    }

    public void setOperation(OrderStatus status) {
        this.operation.set(status);
    }

    public ObjectProperty<OrderStatus> operationProperty() {
        return operation;
    }

	public String isReviewed() {
		if(isReviewed.get()) {
			return "已评价";
		} else {
			return "未评价";
		}
	}

	public int getStayDays() {
		return stayDays;
	}

	public String getLeaveTime() {
		return transformTime(leaveTime);
	}

    public OrderVo getOrderVo() {
		return orderVo;
	}
    
    public String hasChildren() {
    	if(hasChildren.get()) {
    		return "有";
    	} else {
    		return "无";
    	}
    }

}
