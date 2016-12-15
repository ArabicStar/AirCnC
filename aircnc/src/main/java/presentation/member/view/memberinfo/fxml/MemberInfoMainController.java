package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.member.ClientCenterController;
import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.model.MemberInfoModel;

public class MemberInfoMainController implements Initializable{
	
	@FXML
	private Label id;
	
	@FXML
	private Button modify;
	
	@FXML
	private Label username;
	
	@FXML
	private Label tele;
	
	@FXML
	private Label mobi;
	
	@FXML
	private Label email;
	
	@FXML
	private Label credit;
	
	@FXML
	private Label typeIndex;
	
	@FXML
	private Label typeContent;
	
	private ClientCenterController controller;
	
	private UserInfoManager manager;
	
	private MemberInfoModel model;
	
	/**
	 * initialize the pane.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initMemberInfo();
			  }
		});
		manager = MemberInfoManagerImpl.getInstance();
	}
	
	@FXML
	public void handleModify(){
		controller.addInfoModifyPane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(ClientCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
	
	private void initMemberInfo(){
		model = manager.getMemberInfo();
		id.setText(model.getID());
		username.setText(model.getUsername());
		tele.setText(model.getTele());
		mobi.setText(model.getMobi());
		email.setText(model.getEmail());
		credit.setText(String.valueOf(model.getCredit()));
		typeIndex.setText(model.getType());
		typeContent.setText(model.getTypeContent());
	}
}
