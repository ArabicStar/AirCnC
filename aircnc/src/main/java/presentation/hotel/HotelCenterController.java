package presentation.hotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.view.HotelMainPane;
import presentation.hotel.view.orderExecute.OrderExecutePane;
import presentation.hotel.view.orderExecute.UnexecutedOrderModel;
import vo.hotel.HotelVo;


public class HotelCenterController extends Application{
	private Stage primaryStage;
	private Scene scene;
	private BorderPane rootLayout;
	
	private HotelMainPane mainClient;
	private OrderExecutePane orderExecutePane;
	
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
