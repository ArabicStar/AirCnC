package presentation.hotel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.view.HotelMainPane;
import presentation.hotel.view.abnormalOrder.AbnormalOrderPane;
import presentation.hotel.view.checkInLive.CheckInLivePane;
import presentation.hotel.view.checkOut.CheckOutPane;
import presentation.hotel.view.hotelInfo.HotelInfoController;
import presentation.hotel.view.hotelInfo.HotelInfoMainPane;
import presentation.hotel.view.hotelInfo.HotelInfoModifyPane;
import presentation.hotel.view.orderBrowse.OrderBrowsePane;
import presentation.hotel.view.orderExecute.OrderExecutePane;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;


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
	
	private InfoManager hotelInfoManager;
	
	private HotelTest test;
	
	
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

		// addSignInPane();

		test = new HotelTest();
		initialize();
		primaryStage.show();
		
	}
	
	public void initialize(){
		if(!InfoModifyAccessorImpl.isLaunched())
			InfoModifyAccessorImpl.launch();
	}
	
	public void addOrderExecutePane() {
		clearContent();
		orderExecutePane = new OrderExecutePane();
		mainClient.getBorderPane().setCenter(orderExecutePane.getPane());
		orderExecutePane.getController().setCenterController(this);	
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
		hotelInfoManager = test.getHotelData();
		clearContent();
		modifyPane = new HotelInfoModifyPane();
		mainClient.getBorderPane().setCenter(modifyPane.getPane());
		modifyPane.getController().setCenterController(this);
		modifyPane.getController().setManager(hotelInfoManager);
		modifyPane.getController().setRootLayout(rootLayout);
	}
	
	public void addHotelInfoMainPane() {
		
		hotelInfoManager = test.getHotelData();
		clearContent();
		infoMainPane = new HotelInfoMainPane();
		mainClient.getBorderPane().setCenter(infoMainPane.getBorderPane());
		infoMainPane.getController().setCenterController(this);	
		infoMainPane.getController().setManager(hotelInfoManager);
		HotelInfoController infoController = new HotelInfoController(infoMainPane,this);
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

}
