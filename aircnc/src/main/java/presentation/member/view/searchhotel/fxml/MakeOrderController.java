package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.searchhotel.MakeOrderNextPane;
import presentation.member.view.searchhotel.MakeOrderPane;

public class MakeOrderController implements Initializable{
	
	@FXML
	private HBox checkInTimeHbox;
	
	@FXML
	private HBox checkOutTimeHbox;
	
	@FXML
	private HBox latestTimeHbox;
	
	@FXML
	private ComboBox<String> roomType;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private TextField peopleNum;
	
	@FXML
	private RadioButton hasChild;
	
	@FXML
	private RadioButton notHasChild;
	
	private MemberSearchHotelGeneralController controller;
	private SearchHotelsModel model;
	private AnchorPane rootLayout;
	private MakeOrderNextPane nextPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleNext(){
		nextPane = new MakeOrderNextPane();
		rootLayout.getChildren().add(nextPane.getPane());
		AnchorPane.setTopAnchor(nextPane.getPane(), 0.0);
		nextPane.getController().setParentController(this.controller);
	}
	
	@FXML
	public void handleClose(){
		controller.removeReverse();
	}
	
	public void setController(MemberSearchHotelGeneralController controller){
		this.controller = controller;
	}
	
	public void setHotelVo(SearchHotelsModel model){
		this.model = model;
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}

}
