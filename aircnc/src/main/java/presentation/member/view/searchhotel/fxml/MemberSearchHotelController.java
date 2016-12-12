package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.member.ClientCenterController;
import presentation.member.accessor.SupremeSearchAccessor;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.SearchHotelManager;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
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
	
	private ClientCenterController controller;
	private SearchHotelManager manager;
	private SupremeSearchAccessor supremeSearchAccessor;
	
	private SupremeSearchPane supremeSearchPane;
	private AnchorPane rootLayout;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleSupreme(){
		addSupremeSearch();
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	public void setManager(SearchHotelManager manager){
		this.manager = manager;
	}
	
	public void addSupremeSearch(){
		supremeSearchAccessor = new SupremeSearchAccessorImpl();
		supremeSearchPane = new SupremeSearchPane();
		rootLayout.getChildren().add(supremeSearchPane.getPane());
		AnchorPane.setTopAnchor(supremeSearchPane.getPane(), 100.0);
		//(infoModify.getPane());
		supremeSearchPane.getController().setController(this);
		supremeSearchPane.getController().setAccessor(supremeSearchAccessor);
	}
	
	public void removeSupremeSearch(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
}
