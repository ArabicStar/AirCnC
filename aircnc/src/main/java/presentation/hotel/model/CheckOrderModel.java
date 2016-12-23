package presentation.hotel.model;

import java.time.LocalDateTime;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import presentation.hotel.utils.cell.ButtonName;
import vo.order.OrderVo;

public class CheckOrderModel {
	private final StringProperty userName;
	private final StringProperty userId;
	private final StringProperty orderId;
	private final StringProperty checkinTime;
	private final StringProperty status;
	private final StringProperty totalPrice;
	private final ObjectProperty<ButtonName> operation;
	
	private Button check;

	/**
	 * Default constructor.
	 */
	public CheckOrderModel() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param userName
	 * @param userId
	 * @param orderId_c
	 * @param checkinTime
	 * @param status
	 * @param totalPrice
	 */
	public CheckOrderModel(OrderVo order) {
		initialButton();
		this.userName = new SimpleStringProperty(order.getMember().getName());
		this.userId = new SimpleStringProperty(String.valueOf(order.getMember().getId()));
		this.orderId = new SimpleStringProperty(String.valueOf(order.getOrderId()));
		this.checkinTime = new SimpleStringProperty(transformTime(order.getEntryTime()));
		this.status = new SimpleStringProperty(order.getStatus().toString());
		this.totalPrice = new SimpleStringProperty(String.valueOf(order.getOriginalPrice()) + "元");
		this.operation = new SimpleObjectProperty<ButtonName>();

	}

	public CheckOrderModel(String s1, String s2, String s3, String s4, String s5, String s6) {
		initialButton();
		this.userName = new SimpleStringProperty(s1);
		this.userId = new SimpleStringProperty(s2);
		this.orderId = new SimpleStringProperty(s3);
		this.checkinTime = new SimpleStringProperty(s4);
		this.status = new SimpleStringProperty(s5);
		this.totalPrice = new SimpleStringProperty(s6);
		this.operation = new SimpleObjectProperty<ButtonName>();
	}
	
	public void initialButton(){
		check = new Button("查看");
		check.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		check.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
           	 Alert alert = new Alert(AlertType.CONFIRMATION);
           	 alert.setTitle("确认信息");
           	 alert.setHeaderText("请进行确认");
           	 alert.setContentText("你确定吗？");
           	 
           	 Optional<ButtonType> result = alert.showAndWait();
           	 if (result.get() == ButtonType.OK){
           		 
           	 } else {
           	     // 
           	 }
            }
        });
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

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String newStatus) {
		this.status.set(newStatus);
	}

	public StringProperty statusProperty() {
		return status;
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

    public ButtonName getOperation() {
        return operation.get();
    }

    public void setOperation(ButtonName name) {
        this.operation.set(name);
    }

    public ObjectProperty<ButtonName> operationProperty() {
        return operation;
    }
}
