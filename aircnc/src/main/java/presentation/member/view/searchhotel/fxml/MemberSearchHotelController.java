package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.member.ClientCenterController;
import presentation.member.accessor.SupremeSearchAccessor;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
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
	
	private ClientCenterController controller;
	private SearchHotelManager manager;
	
	private SupremeSearchPane supremeSearchPane;
	private AnchorPane rootLayout;
	private AnchorPane searchLayout;
	
	private int hotelNum;
	private ObservableList<SearchHotelsModel> list;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.manager = SearchHotelManagerImpl.getInstance();
		this.hotelNum = 0;
	}
	
	@FXML
	public void handleSupreme(){
		addSupremeSearch();
	}
	
	@FXML
	public void handleAddGeneralInfo(){
		this.hotelNum = 0;
		list = manager.getHotelList();
		Iterator<SearchHotelsModel> it = list.iterator();
		while(it.hasNext()){
			MemberSearchHotelGeneralPane newPane = new MemberSearchHotelGeneralPane(it.next());
			searchLayout.getChildren().add(newPane.getPane());
			AnchorPane.setTopAnchor(newPane.getPane(), 110.0 + (this.hotelNum)*140);
			AnchorPane.setLeftAnchor(newPane.getPane(), 30.0);
			newPane.getController().setController(this);
			this.hotelNum+=1;
		}
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	public void setSearchLayout(AnchorPane pane){
		this.searchLayout = pane;
	}
	
	public void addSupremeSearch(){
		if(!SupremeSearchAccessorImpl.isLaunched())
			SupremeSearchAccessorImpl.launch();
		supremeSearchPane = new SupremeSearchPane();
		rootLayout.getChildren().add(supremeSearchPane.getPane());
		AnchorPane.setTopAnchor(supremeSearchPane.getPane(), 100.0);
		//(infoModify.getPane());
		supremeSearchPane.getController().setController(this);
	}
	
	public void removeSupremeSearch(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
}
