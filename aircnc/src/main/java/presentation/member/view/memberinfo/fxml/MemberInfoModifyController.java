package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.member.ClientCenterController;
import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.manager.UserInfoManager;
import presentation.member.model.MemberInfoModel;
import presentation.member.utils.PlainDialog;

public class MemberInfoModifyController implements Initializable{
	
	@FXML
	private Label id;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField tele;
	
	@FXML
	private TextField phone;
	
	@FXML
	private TextField email;
	
	@FXML
	private Button back;
	
	@FXML
	private Button confirm;
	
	private ClientCenterController controller;
	
	private InfoModifyAccessor accessor;
	
	private UserInfoManager manager;
	
	private MemberInfoModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  model = manager.getMemberInfo();
				  id.setText(model.getID());
				  name.setText(model.getUsername());
				  tele.setText(model.getTele());
				  phone.setText(model.getMobi());
				  email.setText(model.getEmail());
			  }
		});	
		
	}
	
	@FXML
	public void handleBack(){
		controller.addInfoMainPane();
	}
	
	@FXML
	public void handleConfirm(){
		if(name.getText().length()>0){
			if(!name.getText().equals(model.getUsername())||!tele.getText().equals(model.getTele())
				||!phone.getText().equals(model.getMobi())||!email.getText().equals(model.getEmail())){
			accessor.setName(name.getText());
			accessor.setMobi(phone.getText());
			accessor.setEmail(email.getText());
			accessor.setTele(tele.getText());
			
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"保存成功","已保存修改的信息");
			alert.showDialog();
			}
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"保存信息失败","请输入完整的信息");
			alert.showDialog();
		}
			
	}
	
	/**
	 * set the controller
	 * @param controller
	 */
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}
	
	/**
	 * set the accessor
	 * @param controller
	 */
	public void setAccessor(InfoModifyAccessor accessor){
		this.accessor = accessor;
	}
	
	/**
	 * set the controller
	 * @param controller
	 */
	public void setManager(UserInfoManager manager){
		this.manager = manager;
	}
	
}
