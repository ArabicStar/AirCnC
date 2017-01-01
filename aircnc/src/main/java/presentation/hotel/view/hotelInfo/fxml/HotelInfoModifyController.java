package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelInfoInteractor;
import interactor.impl.hotel.HotelInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.InfoModifyAccessor;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.manager.impl.InfoManagerImpl;
import presentation.hotel.model.HotelInfoModel;
import presentation.hotel.utils.dialog.ModifyPasswordDialog;
import presentation.hotel.utils.dialog.PlainDialog;
import presentation.hotel.view.hotelInfo.ModifyRoomPane;

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
	private Button password;
	
	@FXML
	private TextArea equipment;
	
	private HotelCenterController controller;
	
	private InfoManager manager;
	
	InfoModifyAccessor accessor;
	
	private HotelInfoModel model;
	
	private ModifyRoomPane addRoomPane;
	
	private Pane rootLayout;
	
	private AnchorPane addRoomLayout;
	
	private HotelInfoInteractor interactor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = InfoManagerImpl.getInstance();
		
		accessor = InfoModifyAccessorImpl.getInstance();
		
		interactor = HotelInfoCourier.getInstance();
		
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
			
			interactor.updateHotel();
			
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"保存信息失败","请输入完整的信息");
			alert.showDialog();
		}
	}
	
	@FXML
	public void handleRoomModify(){
		
		addModifyRoom();
	}
	
	@FXML
	public void handlePasswordModify(){
		//这里逻辑处理一下
		@SuppressWarnings("unused")
		ModifyPasswordDialog modifyPassword = new ModifyPasswordDialog();
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	private void initHotelInfo(){
		interactor.getHotelInfo();
		model = manager.getHotelInfo();
		this.scope.setText(model.getScope());
		this.id.setText(model.getId());
		this.location.setText(model.getLocation());
		this.introduction.setText(model.getIntro());
		this.equipment.setText(model.getEquip());
		
	}
	
	public void setRootLayout(Pane pane){
		this.rootLayout = pane;
	}
	
	public void setRoomLayout(AnchorPane pane){
		this.addRoomLayout = pane;
	}
	
	public void addModifyRoom(){
		addRoomPane = new ModifyRoomPane();
		addRoomPane.getPane().setLayoutX(300);
		addRoomPane.getPane().setLayoutY(150);
		rootLayout.getChildren().add(addRoomPane.getPane());
		
		addRoomPane.getController().setController(this);
	}
	
	public void removeModifyRoom(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
}
