package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.member.ClientCenterController;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.searchhotel.MemberSearchHotelGeneralPane;
import presentation.member.view.searchhotel.SupremeSearchPane;

/**
 * the controller of hotel search (main).
 * @author paranoia
 *
 */
public class MemberSearchHotelController implements Initializable{
	
	/*
	 * 记录点东西：酒店框长720 宽130 第一个坐标30，110 ，往下依次y坐标加150
	 */
	
	@FXML
	private TextField hotelName;
	
	@FXML
	private Label supreme;
	
	@FXML
	private Button add;
	
	@FXML
	private Button preButton;
	
	@FXML
	private Button nextButton;
	
	@FXML
	private VBox searchedResult;
	
	private ClientCenterController controller;
	private SearchHotelManager manager;
	
	private SupremeSearchPane supremeSearchPane;
	private AnchorPane rootLayout;
	
	private int hotelNum;
	private ObservableList<SearchHotelsModel> list;
	private int pageNum;
	private int maxPageNum;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.manager = SearchHotelManagerImpl.getInstance();
		this.hotelNum = 0;
		this.pageNum = 0;
		this.maxPageNum = 0;
		preButton.setDisable(true);
		nextButton.setDisable(true);
	}
	
	@FXML
	public void handleSupreme(){
		addSupremeSearch();
	}
	
	@FXML
	public void handleAddGeneralInfo(){
		searchedResult.getChildren().clear();
		this.hotelNum = 0;
		list = manager.getHotelList(pageNum);
		this.hotelNum = manager.getSearchedNum();
		this.maxPageNum = (hotelNum-1)/4;
		int index = 0;
		Iterator<SearchHotelsModel> it = list.iterator();
		while(it.hasNext()){
			MemberSearchHotelGeneralPane newPane = new MemberSearchHotelGeneralPane(it.next());
			searchedResult.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
			index+=1;
		}
		if(maxPageNum > 0)
			nextButton.setDisable(false);
	}
	
	@FXML
	public void handlePrePage(){
		searchedResult.getChildren().clear();
		this.pageNum -= 1;
		if(this.pageNum == 0)
			preButton.setDisable(true);
		nextButton.setDisable(false);
		list = manager.getHotelList(pageNum);
		Iterator<SearchHotelsModel> it = list.iterator();
		while(it.hasNext()){
			MemberSearchHotelGeneralPane newPane = new MemberSearchHotelGeneralPane(it.next());
			searchedResult.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
		}
	}
	
	@FXML
	public void handleNextPage(){
		searchedResult.getChildren().clear();
		this.pageNum += 1;
		if(this.pageNum == this.maxPageNum)
			nextButton.setDisable(true);
		preButton.setDisable(false);
		list = manager.getHotelList(pageNum);
		Iterator<SearchHotelsModel> it = list.iterator();
		while(it.hasNext()){
			MemberSearchHotelGeneralPane newPane = new MemberSearchHotelGeneralPane(it.next());
			searchedResult.getChildren().add(newPane.getPane());
			newPane.getController().setController(this);
		}		
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	public void addSupremeSearch(){
		if(!SupremeSearchAccessorImpl.isLaunched())
			SupremeSearchAccessorImpl.launch();
		supremeSearchPane = new SupremeSearchPane();
		rootLayout.getChildren().add(supremeSearchPane.getPane());
		AnchorPane.setTopAnchor(supremeSearchPane.getPane(), 100.0);
		supremeSearchPane.getController().setController(this);
	}
	
	public void removeSupremeSearch(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
}
