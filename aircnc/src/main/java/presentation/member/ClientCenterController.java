package presentation.member;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.memberinfo.MemberInfoMainPane;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
import presentation.member.view.myorder.MemberOrderMainPane;
import presentation.member.view.searchhotel.MemberSearchHotelPane;
import presentation.member.model.CreditModel;
import presentation.member.model.MyorderModel;
import presentation.member.view.MemberMainPane;
import presentation.member.view.creditchange.MemberCreditChangePane;

/**
 * the controller of the whole client.
 * 
 * @author paranoia
 *
 */
public class ClientCenterController extends Application {

	private Stage primaryStage;
	private Scene scene;
	private MemberMainPane mainClient;
	private MemberInfoMainPane infoMain;
	private MemberInfoModifyPane infoModify;
	private MemberCreditChangePane creditMain;
	private MemberSearchHotelPane searchMain;
	private MemberOrderMainPane orderMain;

	private AnchorPane rootLayout;
	private BorderPane mainPane;
	private AnchorPane content;

	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");

		// show the pane of sign in.
		mainClient = new MemberMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getAnchorPane();
		mainPane = (BorderPane) rootLayout.getChildren().get(0);
		content = (AnchorPane) mainPane.getChildren().get(1);
		scene = new Scene(mainClient.getAnchorPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);

		// addSignInPane();

		primaryStage.show();

	}

	public void addInfoMainPane() {
		content.getChildren().clear();
		infoMain = new MemberInfoMainPane();
		content.getChildren().add(infoMain.getContentPane());
		AnchorPane.setTopAnchor(infoMain.getContentPane(),10.0);
		infoMain.getController().setCenterController(this);
	}

	public void addInfoModifyPane() {
		content.getChildren().clear();
		infoModify = new MemberInfoModifyPane();
		AnchorPane.setTopAnchor((infoModify.getPane()), 10.0);
		//(infoModify.getPane());
		infoModify.getController().setCenterController(this);
	}

	public void addCreditChangePane() {
		content.getChildren().clear();
		creditMain = new MemberCreditChangePane();
		content.getChildren().add(creditMain.getPane());
		creditMain.getController().setCenterController(this);
		//addCreditChangeRecord();
	}
	
	public void addCreditChangeRecord(){
		creditMain.getController().init();
	}

	public void addSearchHotelPane() {
		content.getChildren().clear();
		searchMain = new MemberSearchHotelPane();
		content.getChildren().add(searchMain.getPane());
		searchMain.getController().setCenterController(this);
	}
	
	private ObservableList<MyorderModel> orderData = FXCollections.observableArrayList();
	
	public void addOrderMainPane() {
		content.getChildren().clear();
		orderMain = new MemberOrderMainPane();
		content.getChildren().add(orderMain.getPane());
		orderMain.getController().setCenterController(this);
		
	}
	
	public ObservableList<MyorderModel> getMyOrderData(){
    	ObservableList<MyorderModel> orderData = FXCollections.observableArrayList();
    	orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		orderData.add(new MyorderModel("速吧酒店","2016-10-09","异常","5晚/1间","290元"));
		return orderData;
    }
	
	public ObservableList<CreditModel> getCreditData(){
    	ObservableList<CreditModel> orderData = FXCollections.observableArrayList();
    	
    	orderData.add(new CreditModel("2016-10-09","20:00","于速八酒店的订单001按时完成，增加信用值233"));
    	orderData.add(new CreditModel("2016-10-09","20:00","略略略略略"));
    	orderData.add(new CreditModel("2016-10-09","20:00","于速八酒店的订单001按时完成，增加信用值233"));
    	
		return orderData;
    }

}
