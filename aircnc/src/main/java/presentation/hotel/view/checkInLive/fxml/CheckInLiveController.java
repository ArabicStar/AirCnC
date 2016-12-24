package presentation.hotel.view.checkInLive.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelInfoInteractor;
import interactor.impl.hotel.HotelInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.HotelRoomAccessor;
import presentation.hotel.accessor.impl.HotelRoomAccessorImpl;
import presentation.hotel.manager.HotelRoomManager;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.utils.dialog.PlainDialog;
import utils.info.hotel.Room;

public class CheckInLiveController implements Initializable{
	@FXML
	private Button comfirm;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private ComboBox<String> roomType;
	
	private HotelCenterController controller;
	
	private HotelRoomManager manager;
	
	private HotelRoomAccessor accessor;
	
	private HotelInfoInteractor interactor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		manager = HotelRoomManagerImpl.getInstance();

		accessor = HotelRoomAccessorImpl.getInstance();
		
		interactor = HotelInfoCourier.getInstance();
		interactor.getHotelRooms();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  roomType.getItems().addAll(manager.getNames());
				  roomType.valueProperty().addListener((observable, oldValue, newValue) -> {
					  	if(newValue!=""){
					  		roomNum.setText("");
					  		roomNum.setPromptText("剩余可用"+Integer.toString(manager.getRoomByName(newValue).getRoomNum())+"间");
						}
					});
			  }
		});
	}
	
	@FXML
	public void handleConfirm(){
		String result = checkText();
		if(result==""){
			accessor.setRoomName(roomType.getValue());
			accessor.setRoomNum(-Integer.parseInt(roomNum.getText()));
			accessor.setRoom(manager.getRoomByName(roomType.getValue()));
			
			interactor.liveCheckIn();

			roomNum.setText("");
			roomType.setValue("");
			roomNum.setPromptText("");
		}else{
			PlainDialog alert = new PlainDialog(AlertType.WARNING,
					"线下入住失败",result);
			alert.showDialog();
		}
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	private String checkText(){		
		int i = 0;
		try{
			i = Integer.parseInt(roomNum.getText());
			if(i<=0)
				return "房间数量必须大于0";
		}catch (Exception e){
			return "请正确输入房间数量";
		}
		
		if(roomType.getValue()==null||roomType.getValue()=="")
			return "请选择房间类型";
		
		if(i>manager.getRoomByName(roomType.getValue()).getRoomNum())
			return "该类型房间不足，无法入住";
		return "";
	}
	
	
	
}
