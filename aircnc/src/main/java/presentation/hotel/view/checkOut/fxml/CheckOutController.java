package presentation.hotel.view.checkOut.fxml;

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
import presentation.hotel.accessor.HotelRoomAccessor;
import presentation.hotel.accessor.impl.HotelRoomAccessorImpl;
import presentation.hotel.manager.HotelRoomManager;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.utils.dialog.PlainDialog;

public class CheckOutController implements Initializable{
	@FXML
	private Button comfirm;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private ComboBox<String> roomType;
	
	private HotelCenterController controller;
	
	private HotelRoomManager manager;
	
	private HotelRoomAccessor accessor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!HotelRoomManagerImpl.isLaunched()){
			HotelRoomManagerImpl.launch();
		}
		manager = HotelRoomManagerImpl.getInstance();
		
		if(!HotelRoomAccessorImpl.isLaunched()){
			HotelRoomAccessorImpl.launch();
		}
		accessor = HotelRoomAccessorImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  roomType.getItems().addAll(manager.getNames());
			  }
		});
		
	}
	
	@FXML
	public void handleConfirm(){
		if(checkText(roomNum.getText(),true)&&checkText(roomType.getValue(),false)){
			accessor.setRoomName(roomType.getValue());
			accessor.setRoomNum(-Integer.parseInt(roomNum.getText()));
			
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"退房成功",roomNum.getText()+"间"+roomType.getValue()+"已退房");
			alert.showDialog();
			roomNum.setText("");
			roomType.setValue("");
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"退房失败","请正确输入房间的信息");
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
