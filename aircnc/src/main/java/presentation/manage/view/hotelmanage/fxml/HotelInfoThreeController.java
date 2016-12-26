package presentation.manage.view.hotelmanage.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import presentation.manage.manager.ManageHotelCommentManager;
import presentation.manage.manager.impl.ManageHotelCommentManagerImpl;
import presentation.manage.model.ManageCommentModel;
import presentation.manage.view.hotelmanage.HotelCommentPane;

public class HotelInfoThreeController implements Initializable{
	private HotelInfoMainController controller;
	
	@FXML
	private VBox comments;
	
	private ManageHotelCommentManager manager;
	private ObservableList<ManageCommentModel> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		manager = ManageHotelCommentManagerImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initComment();
			  }
		});
	}
	
	@FXML
	public void handlePage1(){
		controller.addHotelInfoOnePane();
	}
	
	@FXML
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	public void setInfoMainController(HotelInfoMainController controller){
		this.controller=controller;
	}
	
	public void initComment(){
		list = manager.getCommentList();
//		System.out.println(list.get(0).getContent());
		Iterator<ManageCommentModel> it = list.iterator();
		while(it.hasNext()){
			HotelCommentPane newPane = new HotelCommentPane(it.next());
			comments.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
		}
		
	}
}
