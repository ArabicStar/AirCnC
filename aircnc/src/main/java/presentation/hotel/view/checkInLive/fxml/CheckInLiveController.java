package presentation.hotel.view.checkInLive.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.hotel.HotelCenterController;
import presentation.hotel.manager.HotelRoomManager;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.utils.dialog.PlainDialog;

public class CheckInLiveController implements Initializable{
	@FXML
	private Button comfirm;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private ComboBox<String> roomType;
	
	private HotelCenterController controller;
	
	private HotelRoomManager manager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!HotelRoomManagerImpl.isLaunched()){
			HotelRoomManagerImpl.launch();
		}
		manager = HotelRoomManagerImpl.getInstance();
		
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  roomType.getItems().addAll(manager.getNames());
			  }
		});
	}
	
	@FXML
	public void handleConfirm(){
		if(checkText(roomNum.getText(),true)&&checkText(roomType.getValue(),false)){
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"线下入住成功",roomNum.getText()+"间"+roomType.getValue()+"已入住");
			alert.showDialog();
			roomNum.setText("");
			roomType.setValue("");
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"线下入住失败","请正确输入房间的信息");
			alert.showDialog();
		}
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	private boolean checkText(String content,boolean isNum){
		if(content==""||content==null){
			return false;
		}
				
		if(isNum){
			try{
				Integer.parseInt(content);
			}catch (Exception e){
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
