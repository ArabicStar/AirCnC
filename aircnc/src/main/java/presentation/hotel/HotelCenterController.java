package presentation.hotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.view.HotelMainPane;
import presentation.hotel.view.checkInLive.CheckInLivePane;
import presentation.hotel.view.checkOut.CheckOutPane;
import presentation.hotel.view.hotelInfo.HotelInfoController;
import presentation.hotel.view.hotelInfo.HotelInfoMainPane;
import presentation.hotel.view.hotelInfo.HotelInfoModifyPane;
import presentation.hotel.view.orderExecute.OrderExecutePane;
import presentation.hotel.view.orderExecute.UnexecutedOrderModel;
import vo.hotel.HotelVo;


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
	
	
	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;
	
	private HotelVo hotel;
	
	
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

		primaryStage.show();
		
	}
	
	public void addOrderExecutePane() {
		clearContent();
		orderExecutePane = new OrderExecutePane();
		mainClient.getBorderPane().setCenter(orderExecutePane.getPane());
		orderExecutePane.getController().setCenterController(this);	
		orderExecutePane.getController().test();
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
		clearContent();
		modifyPane = new HotelInfoModifyPane();
		mainClient.getBorderPane().setCenter(modifyPane.getPane());
		modifyPane.getController().setCenterController(this);
	}
	
	public void addHotelInfoMainPane() {
		clearContent();
		infoMainPane = new HotelInfoMainPane();
		mainClient.getBorderPane().setCenter(infoMainPane.getBorderPane());
		infoMainPane.getController().setCenterController(this);	
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
	
	public ObservableList<UnexecutedOrderModel> getData(){
    	ObservableList<UnexecutedOrderModel> orderData = FXCollections.observableArrayList();
    	orderData.add(new UnexecutedOrderModel("小手表","233","101","2016-10-09","5晚/1间","290元"));
		orderData.add(new UnexecutedOrderModel("小手表","233","102","2016-10-12","2晚/1间","1000元"));
		orderData.add(new UnexecutedOrderModel("小手表","233","103","2016-10-15","10晚/1间","400元"));
		orderData.add(new UnexecutedOrderModel("小手表","233","104","2016-10-30","1晚/10间","2950元"));
		return orderData;
    }
}
