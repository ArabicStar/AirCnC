package presentation.hotel.view.orderBrowse;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class HotelOrderModel {
	private final StringProperty userName;
	private final StringProperty userId;
	private final StringProperty orderId;
	private final StringProperty checkinTime;
	private final StringProperty timeAndSum;
	private final StringProperty totalPrice;
	private final ObjectProperty<Button> operation;

	/**
	 * Default constructor.
	 */
	public HotelOrderModel() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param userName
	 * @param userId
	 * @param orderId
	 * @param checkinTime
	 * @param timeAndSum
	 * @param totalPrice
	 */
	public HotelOrderModel(OrderVo order) {
		this.userName = new SimpleStringProperty(order.getUserName());
		this.userId = new SimpleStringProperty(String.valueOf(order.getUserId()));
		this.orderId = new SimpleStringProperty(String.valueOf(order.getOrderId()));
		this.checkinTime = new SimpleStringProperty(transformTime(order.getEntryTime()));
		this.timeAndSum = new SimpleStringProperty(order.getStayDays() + "晚/" + order.getRoomNumber() + "间");
		this.totalPrice = new SimpleStringProperty(String.valueOf(order.getPrice()) + "元");
		this.operation = new SimpleObjectProperty<Button>(new Button("执行"));
		
	}
	
	public HotelOrderModel(String s1,String s2,String s3,String s4,String s5,String s6){
		this.userName = new SimpleStringProperty(s1);
		this.userId = new SimpleStringProperty(s2);
		this.orderId = new SimpleStringProperty(s3);
		this.checkinTime = new SimpleStringProperty(s4);
		this.timeAndSum = new SimpleStringProperty(s5);
		this.totalPrice = new SimpleStringProperty(s6);
		this.operation = new SimpleObjectProperty<Button>(new Button("执行"));
	}

	/**
	 * transform the local date time (yyyy/mm/dd HH:mm) to the new format
	 * (yyyy-mm-dd)
	 * 
	 * @param date
	 * @return new date format(String)
	 */

	private static String transformTime(LocalDateTime date) {

		String result = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();

		return result;
	}
	
    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public StringProperty userNameProperty() {
        return userName;
    }
    
    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public StringProperty userIdProperty() {
        return userId;
    }
    
    public String getOrderId() {
        return orderId.get();
    }

    public void setOrderId(String orderId) {
        this.orderId.set(orderId);
    }

    public StringProperty orderIdProperty() {
        return orderId;
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
    
    public Button getOperation() {
        return operation.get();
    }

    public void setOperation(Button newButtons) {
        this.operation.set(newButtons);
    }

    public ObjectProperty<Button> operationProperty() {
        return operation;
    }
}
