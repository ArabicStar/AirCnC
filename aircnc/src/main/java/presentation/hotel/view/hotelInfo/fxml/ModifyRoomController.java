package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	
	HotelInfoModifyController controller;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				minus.setDisable(true);
				plus.setDisable(true);
				name.setDisable(true);
				type.getItems().addAll("单人间","双人间","三人间","其它");
				type.valueProperty().addListener((observable, oldValue, newValue) -> {
				  	if(oldValue!=newValue){
				  		switch (newValue){
				  		case "其它":
				  			minus.setDisable(true);
				  			plus.setDisable(false);
				  			name.setDisable(false);
				  			break;
				  		default:
				  			minus.setDisable(true);
				  			plus.setDisable(true);
				  			name.setDisable(true);
				  			name.setText(newValue);
				  			switch (newValue){
				  			case "单人间":
				  				peopleNum.setText("1");
				  				break;
				  			case "双人间":
				  				peopleNum.setText("2");
				  				break;
				  			case "三人间":
				  				peopleNum.setText("3");
				  				break;
				  			}
				  			break;
				  		}
					}
				});
				
			}
			
		});
		
		
	}
	
	@FXML
	public void close(){
		controller.removeSupremeSearch();
	}
	
	@FXML
	public void handleAddRoom(){
		controller.removeSupremeSearch();
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
	
	public void setController(HotelInfoModifyController controller){
		this.controller = controller;
	}

}
