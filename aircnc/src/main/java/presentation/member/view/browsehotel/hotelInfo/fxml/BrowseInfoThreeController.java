package presentation.member.view.browsehotel.hotelInfo.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import presentation.member.manager.HotelCommentManager;
import presentation.member.manager.impl.HotelCommentManagerImpl;
import presentation.member.model.CommentModel;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.browsehotel.hotelInfo.BrowseCommentPane;

public class BrowseInfoThreeController implements Initializable{
	private BrowseInfoMainController controller;
	
	@FXML
	private VBox comments;
	
	@SuppressWarnings("unused")
	private HotelCommentManager manager;
	private List<CommentModel> list;
	private SearchHotelsModel model;
	
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
	
	public void setInfoMainController(BrowseInfoMainController controller){
		this.controller=controller;
	}
	
	public void initComment(){
		list = model.getComments();
//		System.out.println(list.get(0).getContent());
		Iterator<CommentModel> it = list.iterator();
		while(it.hasNext()){
			BrowseCommentPane newPane = new BrowseCommentPane(it.next());
			comments.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
		}
		
	}
	
	public void setHotelModel(SearchHotelsModel model){
		this.model = model;
	}
}
