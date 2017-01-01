package presentation.hotel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.view.HotelMainPane;
import presentation.hotel.view.abnormalOrder.AbnormalOrderPane;
import presentation.hotel.view.checkInLive.CheckInLivePane;
import presentation.hotel.view.checkOut.CheckOutPane;
import presentation.hotel.view.hotelInfo.HotelInfoController;
import presentation.hotel.view.hotelInfo.HotelInfoMainPane;
import presentation.hotel.view.hotelInfo.HotelInfoModifyPane;
import presentation.hotel.view.hotelPromotion.HotelPromotionMainPane;
import presentation.hotel.view.orderBrowse.OrderBrowsePane;
import presentation.hotel.view.orderExecute.OrderExecutePane;


public class HotelCenterController extends Application{
	private Stage primaryStage;
	private Scene scene;
	private BorderPane rootLayout;
	
	private HotelMainPane mainClient;
	private OrderExecutePane orderExecutePane;
	private HotelInfoMainPane infoMainPane;
	private HotelInfoModifyPane modifyPane;
	private CheckInLivePane checkInPane;
	private CheckOutPane checkOutPane;
	private OrderBrowsePane browsePane;
	private AbnormalOrderPane abnormalPane;
	private HotelPromotionMainPane promotionPane;
	
	private HotelInfoController infoController;
	
//	private InfoManager hotelInfoManager;	
//	private HotelTest test;
	
	
	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		mainClient = new HotelMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getBorderPane();
		scene = new Scene(mainClient.getBorderPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);
//
//		test = new HotelTest();
		primaryStage.show();
		
	}
	
	public void addOrderExecutePane() {
		clearContent();
		orderExecutePane = new OrderExecutePane();
		mainClient.getBorderPane().setCenter(orderExecutePane.getPane());
		orderExecutePane.getController().setCenterController(this);	
	}
	
	public void addHotelPromotionPane() {
		clearContent();
		promotionPane = new HotelPromotionMainPane();
		mainClient.getBorderPane().setCenter(promotionPane.getPane());
		promotionPane.getController().setCenterController(this);
		promotionPane.getController().setRootLayout(rootLayout);
	}
	
	public void addAbnormalOrderPane() {
		clearContent();
		abnormalPane = new AbnormalOrderPane();
		mainClient.getBorderPane().setCenter(abnormalPane.getPane());
		abnormalPane.getController().setCenterController(this);	
	}
	
	public void addOrderBrowsePane() {
		clearContent();
		browsePane = new OrderBrowsePane();
		mainClient.getBorderPane().setCenter(browsePane.getPane());
		browsePane.getController().setCenterController(this);	
	}
	
	public void addCheckInLivePane() {
		clearContent();
		checkInPane = new CheckInLivePane();
		mainClient.getBorderPane().setCenter(checkInPane.getPane());
		checkInPane.getController().setCenterController(this);	
	}
	
	public void addCheckOutPane() {
		clearContent();
		checkOutPane = new CheckOutPane();
		mainClient.getBorderPane().setCenter(checkOutPane.getPane());
		checkOutPane.getController().setCenterController(this);	
	}
	
	public void addHotelModifyPane() {
//		hotelInfoManager = test.getHotelData();
		clearContent();
		modifyPane = new HotelInfoModifyPane();
		mainClient.getBorderPane().setCenter(modifyPane.getPane());
		modifyPane.getController().setCenterController(this);
//		modifyPane.getController().setManager(hotelInfoManager);
		modifyPane.getController().setRootLayout(rootLayout);
	}
	
	public void addHotelInfoMainPane() {
		
		clearContent();
		infoMainPane = new HotelInfoMainPane();
		infoController = new HotelInfoController(infoMainPane,this);
		mainClient.getBorderPane().setCenter(infoMainPane.getBorderPane());
		infoMainPane.getController().setCenterController(this);	
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
	
	public void closeWindow(){
		this.primaryStage.close();
	}

}
