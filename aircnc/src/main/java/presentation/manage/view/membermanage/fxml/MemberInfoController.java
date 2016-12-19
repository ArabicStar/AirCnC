package presentation.manage.view.membermanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.manage.manager.MemberManageInfoManager;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import presentation.manage.model.MemberManageModel;

public class MemberInfoController implements Initializable{
	
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
	
	@FXML
	private Button back;
	
	private MemberManageMainController controller;
	
	private MemberManageInfoManager manager;
	
	private MemberManageModel model;
	
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
		manager = MemberManageInfoImpl.getInstance();
	}
	
	@FXML
	public void handleBack(){
		controller.removeInfoPane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setController(MemberManageMainController controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
	}
	
	public void setModel(MemberManageModel model){
		this.model = model;
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
