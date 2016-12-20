package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import presentation.member.manager.HotelCommentManager;
import presentation.member.manager.impl.HotelCommentManagerImpl;
import presentation.member.model.CommentModel;
import presentation.member.view.searchhotel.hotelInfo.HotelCommentPane;

public class HotelInfoThreeController implements Initializable{
	private HotelInfoMainController controller;
	
	@FXML
	private VBox comments;
	
	private HotelCommentManager manager;
	private ObservableList<CommentModel> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(!HotelCommentManagerImpl.isLaunched()){
			HotelCommentManagerImpl.launch();
		}
		manager = HotelCommentManagerImpl.getInstance();
		
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
	
	@FXML
	public void handlePage3(){
		controller.addHotelInfoThreePane();
	}
	
	public void setInfoMainController(HotelInfoMainController controller){
		this.controller=controller;
	}
	
	public void initComment(){
		list = manager.getCommentList();
//		System.out.println(list.get(0).getContent());
		Iterator<CommentModel> it = list.iterator();
		while(it.hasNext()){
			HotelCommentPane newPane = new HotelCommentPane(it.next());
			comments.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
		}
		
	}
}
