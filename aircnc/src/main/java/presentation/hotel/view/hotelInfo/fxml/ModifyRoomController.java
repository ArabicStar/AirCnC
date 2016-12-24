package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelInfoInteractor;
import interactor.impl.hotel.HotelInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.hotel.accessor.InfoModifyAccessor;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.manager.HotelRoomManager;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.utils.dialog.PlainDialog;
import utils.info.hotel.Room;

public class ModifyRoomController implements Initializable{
	@FXML
	private ComboBox<String> type;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private TextField price;
	
	@FXML
	private Label peopleNum;
	
	@FXML
	private Button minus;
	
	@FXML
	private Button plus;
	
	@FXML
	private Button modify;
	
	HotelInfoModifyController controller;
	
	InfoModifyAccessor accessor;
	
	HotelRoomManager manager;
	
	HotelInfoInteractor interactor;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = HotelRoomManagerImpl.getInstance();
		
		accessor = InfoModifyAccessorImpl.getInstance();
		
		interactor = HotelInfoCourier.getInstance();
		interactor.getHotelRooms();

		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				minus.setDisable(true);
				plus.setDisable(true);
				name.setDisable(true);
				type.getItems().add("其它");
				type.getItems().addAll(manager.getNames());
				type.valueProperty().addListener((observable, oldValue, newValue) -> {
				  	if(oldValue!=newValue){
				  		switch (newValue){
				  		case "其它":
				  			minus.setDisable(true);
				  			plus.setDisable(false);
				  			name.setDisable(false);
				  			name.setText("");
				  			price.setText("");
				  			roomNum.setText("");
				  			peopleNum.setText("1");
				  			modify.setText("添加房间");
				  			break;
				  		default:
				  			minus.setDisable(true);
				  			plus.setDisable(true);
				  			name.setDisable(true);
				  			name.setText(newValue);
				  			Room r = manager.getRoomByName(newValue);
				  			peopleNum.setText(Integer.toString(r.getPeopleNum()));
				  			price.setText(Double.toString(r.getPrice()));
				  			roomNum.setText(Integer.toString(r.getRoomNum()));
				  			modify.setText("修改房间");
				  		}
					}
				});
				
			}
			
		});
		
		
	}
	
	@FXML
	public void close(){
		controller.removeModifyRoom();
	}
	
	@FXML
	public void handleAddRoom(){
		String result = checkText();
		if(result==""){

			accessor = InfoModifyAccessorImpl.getInstance();
			accessor.setRoom(name.getText(), Integer.parseInt(peopleNum.getText()),
					Integer.parseInt(roomNum.getText()),Double.parseDouble(price.getText()));
			interactor.updateHotel();
			controller.removeModifyRoom();
			}else{
			PlainDialog alert = new PlainDialog(AlertType.WARNING,
					"添加失败",result);
			alert.showDialog();
		}
		
		
	}
	
	@FXML
	public void handleMinus(){
		int num = Integer.parseInt(peopleNum.getText());
		peopleNum.setText(Integer.toString(num-1));
		plus.setDisable(false);
		if(num<=1){
			minus.setDisable(true);
		}
	}
	
	@FXML
	public void handlePlus(){
		int num = Integer.parseInt(peopleNum.getText());
		peopleNum.setText(Integer.toString(num+1));
		minus.setDisable(false);
		if(num>=12){
			plus.setDisable(true);
		}
	}
	
	private String checkText(){
		try{
			int i = Integer.parseInt(roomNum.getText());
			if(i<=0)
				return "房间数量必须大于0";
		}catch (Exception e){
			return "请正确输入房间数量";
		}
		
		try{
			double i = Double.parseDouble(price.getText());
			if(i<=0)
				return "房间价格必须大于0";
		}catch (Exception e){
			return "请正确输入房间价格";
		}
		
		if(type.getValue()=="其它"&&manager.getNames().stream().filter(n->n.equals(name.getText())).iterator().hasNext())
			return "请勿输入已有的房间类型";
		return "";
	}
	
	public void setController(HotelInfoModifyController controller){
		this.controller = controller;
	}

}
