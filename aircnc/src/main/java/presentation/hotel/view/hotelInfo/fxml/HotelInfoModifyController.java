package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.InfoModifyAccessor;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;
import presentation.member.utils.dialog.PlainDialog;

public class HotelInfoModifyController implements Initializable{
	@FXML
	private Label id;
	
	@FXML
	private TextField scope;
	
	@FXML
	private TextField location;
	
	@FXML
	private TextArea introduction;	
	
	@FXML
	private Button back;
	
	@FXML
	private Button confirm;
	
	@FXML
	private Button modifyRoom;
	
	@FXML
	private TextArea equipment;
	
	private HotelCenterController controller;
	
	private InfoManager manager;
	
	InfoModifyAccessor accessor;
	
	private HotelInfoModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		introduction.setWrapText(true);
		equipment.setWrapText(true);

		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initHotelInfo();
			  }
		});
	}
	
	@FXML
	public void handleBack(){
		controller.addHotelInfoMainPane();
	}
	
	@FXML
	public void handleConfirm(){
		//avoid using Chinese semicolon
		equipment.setText(equipment.getText().replace('；', ';'));
		
		
		if(!scope.getText().equals(model.getScope())||!introduction.getText().equals(model.getIntro())
			||!location.getText().equals(model.getLocation())||!equipment.getText().equals(model.getEquip())){
			accessor.setEquip(equipment.getText());
			accessor.setIntro(introduction.getText());
			accessor.setLocation(location.getText());
			accessor.setScope(scope.getText());
			
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"保存成功","已保存修改的信息");
			alert.showDialog();
			
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"保存信息失败","请输入完整的信息");
			alert.showDialog();
		}
	}
	
	@FXML
	public void handleRoomModify(){
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	/**
	 * set the hotel info manager
	 * aiming to fetch the hotel info model
	 * @param manager
	 */
	public void setManager(InfoManager manager){
		this.manager = manager;
	}
	
	private void initHotelInfo(){
		model = manager.getHotelInfo();
		this.scope.setText(model.getScope());
		this.id.setText(model.getId());
		this.location.setText(model.getLocation());
		this.introduction.setText(model.getIntro());
		this.equipment.setText(model.getEquip());
		
	}
	
}
