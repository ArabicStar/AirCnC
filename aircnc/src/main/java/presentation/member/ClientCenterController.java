package presentation.member;

import java.util.Optional;

import interactor.impl.member.HotelSearchCourier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.memberinfo.MemberInfoMainPane;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
import presentation.member.view.myorder.MemberOrderMainPane;
import presentation.member.view.searchhotel.MemberSearchHotelPane;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import presentation.member.utils.dialog.TextFieldDialog;
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
	private AnchorPane content;

	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");

		// show the pane of sign in.
		mainClient = new MemberMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getAnchorPane();
		content = (AnchorPane)rootLayout.getChildren().get(1);
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
		if(!InfoModifyAccessorImpl.isLaunched())
			InfoModifyAccessorImpl.launch();
		content.getChildren().clear();
		infoModify = new MemberInfoModifyPane();
		content.getChildren().add(infoModify.getPane());
		AnchorPane.setTopAnchor((infoModify.getPane()), 10.0);
		//(infoModify.getPane());
		infoModify.getController().setCenterController(this);
	}

	public void addCreditChangePane() {
		content.getChildren().clear();
		creditMain = new MemberCreditChangePane();
		content.getChildren().add(creditMain.getPane());
		AnchorPane.setTopAnchor(creditMain.getPane(), 10.0);
		creditMain.getController().setCenterController(this);
		//addCreditChangeRecord();
	}

	public void addSearchHotelPane() {
		content.getChildren().clear();
		if(!SearchHotelManagerImpl.isLaunched())
			SearchHotelManagerImpl.launch();
		TextFieldDialog dialog = new TextFieldDialog("搜索酒店","商圈：");
		
		Optional<String> result = dialog.showDialog();
		
		if(result.isPresent()){
			HotelSearchAccessorImpl.getInstance().setScope(result.get());
			HotelSearchCourier.getInstance().searchByCondition();
			HotelSearchAccessorImpl.getInstance().setScope(null);
			searchMain = new MemberSearchHotelPane();
			content.getChildren().add(searchMain.getPane());
			AnchorPane.setTopAnchor(searchMain.getPane(), 0.0);
			searchMain.getController().setCenterController(this);
			searchMain.getController().setRootLayout(content);			
		}else{
			mainClient.getController().setSearchHotelDisable(false);
		}
	}
	
	public void addOrderMainPane() {
		content.getChildren().clear();
		orderMain = new MemberOrderMainPane();
		content.getChildren().add(orderMain.getPane());
		AnchorPane.setTopAnchor(orderMain.getPane(), 10.0);
		orderMain.getController().setCenterController(this);
		orderMain.getController().setRootLayout(content);
	}
	
	public void initializeLogin(){
		primaryStage.close();
		CenterController login = new CenterController();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeWindow(){
		primaryStage.close();
	}

}
