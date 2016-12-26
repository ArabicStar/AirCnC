package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.searchhotel.fxml.MemberSearchHotelGeneralController;
import presentation.member.view.searchhotel.hotelInfo.HotelInfoOnePane;
import presentation.member.view.searchhotel.hotelInfo.HotelHistoryOrdersPane;
import presentation.member.view.searchhotel.hotelInfo.HotelInfoFourPane;
import presentation.member.view.searchhotel.hotelInfo.HotelInfoTwoPane;

/**
 * 这里的图片路径要改
 * @author paranoia
 *
 */
public class HotelInfoMainController implements Initializable{
	@FXML
	private Label grade;
	
	@FXML
	private Label name;
	
	@FXML
	private ImageView hotelImage;
	
	@FXML
	private Button back;
	
	private MemberSearchHotelGeneralController controller;
	private BorderPane contentLayout;
	
	private AnchorPane infoMainPane;
	private HotelInfoOnePane onePane;
	private HotelInfoTwoPane twoPane;
	private HotelHistoryOrdersPane threePane;
	private HotelInfoFourPane fourPane;
	private SearchHotelsModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  contentLayout = (BorderPane) infoMainPane.getChildren().get(0);
				  initHotelInfo();
				  addHotelInfoOnePane();
				  HotelSearchAccessorImpl.getInstance().setName(model.getHotelName());
			  }
		});
		
	}
	
	@FXML
	public void handleBack(){
		controller.removeDetailedInfo();
	}
	
	public void setController(MemberSearchHotelGeneralController controller){
		this.controller=controller;
	}
	
	@FXML
	public void addHotelInfoOnePane() {
		clearContent();
		onePane = new HotelInfoOnePane();
		contentLayout.setCenter(onePane.getPane());
		onePane.getController().setInfoMainController(this);	
		onePane.getController().setModel(model);
	}
	
	@FXML
	public void addHotelInfoTwoPane() {
		clearContent();
		twoPane = new HotelInfoTwoPane();
		contentLayout.setCenter(twoPane.getPane());
		twoPane.getController().setInfoMainController(this);
	}
	
	@FXML
	public void addHotelInfoThreePane() {
		clearContent();
		threePane = new HotelHistoryOrdersPane();
		contentLayout.setCenter(threePane.getPane());
		threePane.getController().setInfoMainController(this);
	}
	
	@FXML
	public void addHotelInfoFourPane() {
		clearContent();
		fourPane = new HotelInfoFourPane();
		contentLayout.setCenter(fourPane.getPane());
		fourPane.getController().setInfoMainController(this);	
	}
	
	/**
	 * remove all the children nodes of the main border pane, except the
	 * nav-bar.
	 */
	public void clearContent() {
		int childrenAmount = contentLayout.getChildren().size();
		if (childrenAmount > 1) {
			for (int i = 1; i < childrenAmount; i++) {
				contentLayout.getChildren().remove(i);
			}
		}
	}
	
	public void setModel(SearchHotelsModel model){
		this.model = model;
	}
	
	public void setInfoMainPane(AnchorPane pane){
		this.infoMainPane = pane;
	}
	
	private void initHotelInfo(){
		name.setText(model.getHotelName());
		grade.setText(String.valueOf(model.getHotelGrade()));
		URL location = getClass().getResource("/images/hotel/star/hotel-"+model.getStar()+".png");
		hotelImage.setImage(new Image(String.valueOf(location)));
		
	}
	
	
}
