package presentation.member;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.memberinfo.MemberInfoMainPane;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
import presentation.member.view.myorder.MemberOrderMainPane;
import presentation.member.view.myorder.MyorderModel;
import presentation.member.view.searchhotel.MemberSearchHotelPane;
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

	private BorderPane rootLayout;

	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		mainClient = new MemberMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getBorderPane();
		scene = new Scene(mainClient.getBorderPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);

		// addSignInPane();

		primaryStage.show();

	}

	public void addInfoMainPane() {
		clearContent();
		infoMain = new MemberInfoMainPane();
		mainClient.getBorderPane().setCenter(infoMain.getContentPane());
		infoMain.getController().setCenterController(this);
	}

	public void addInfoModifyPane() {
		clearContent();
		infoModify = new MemberInfoModifyPane();
		mainClient.getBorderPane().setCenter(infoModify.getPane());
		infoModify.getController().setCenterController(this);
	}

	public void addCreditChangePane() {
		clearContent();
		creditMain = new MemberCreditChangePane();
		mainClient.getBorderPane().setCenter(creditMain.getPane());
		creditMain.getController().setCenterController(this);
	}

	public void addSearchHotelPane() {
		clearContent();
		searchMain = new MemberSearchHotelPane();
		mainClient.getBorderPane().setCenter(searchMain.getPane());
		searchMain.getController().setCenterController(this);
	}
	
	private ObservableList<MyorderModel> orderData = FXCollections.observableArrayList();
	
	public void addOrderMainPane() {
		clearContent();
		orderMain = new MemberOrderMainPane();
		mainClient.getBorderPane().setCenter(orderMain.getPane());
		orderMain.getController().setCenterController(this);
		
	}

	/**
	 * remove all the children nodes of the main border pane, except the
	 * nav-bar.
	 */
	public void clearContent() {
		int childrenAmount = mainClient.getBorderPane().getChildren().size();
		if (childrenAmount > 1) {
			for (int i = 1; i < childrenAmount; i++) {
				mainClient.getBorderPane().getChildren().remove(i);
			}
		}
	}
	
	public ObservableList<MyorderModel> getData(){
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

}
