package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import presentation.member.CenterController;
import presentation.member.accessor.RegisterEnterpriseAccessor;
import presentation.member.accessor.RegisterPersonAccessor;
import presentation.member.utils.PlainDialog;



/**
 * this is the controller of register main pane.
 * @author paranoia
 *
 */

public class MemberRegisterMainController implements Initializable{
	
	@FXML
    private RadioButton personal;
	
	@FXML
    private RadioButton business;
	
	@FXML
    private TextField username;

	@FXML
    private PasswordField password;

	@FXML
    private PasswordField confirmPassword;
	
	@FXML
	private Button next;
	
	@FXML
	private Button back;
	
	private ToggleGroup memberType;
	
	private CenterController controller;
	
	private RegisterPersonAccessor accessorPerson;
	private RegisterEnterpriseAccessor accessorEnterprise;
	
	/**
	 * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberType = new ToggleGroup();
		personal.setToggleGroup(memberType);
		business.setToggleGroup(memberType);
		
		username.setPromptText("用户名");
		password.setPromptText("密码");
		confirmPassword.setPromptText("确认密码");
		
		next.setDisable(true);
		//监听键入,输入的除去空格后有内容则恢复按钮
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    next.setDisable(newValue.trim().isEmpty());
		});
	}
	
	/**
	 * handle the button action (Next)
	 * check the input value
	 * if the value is valid, check the choice box.
	 *   if person, jump to personal register pane.(MemberRegisterPersonPane)
	 *   if enterprise, jump to personal register pane.(MemberRegisterEnterprisePane)
	 * otherwise, pop up error message.
	 */
	@FXML
	public void handleNext(){
		//验证用户填写的正确性，正确就将内容传到逻辑层，逻辑层存入数据库，然后跳至下一个注册界面
		//检查单选的选择，选择1则跳至memberRegsiterPerson，选择2则跳至memberRegisterBusiness
		if(username.getText().length()!=0&&password.getText().length()!=0){
			if(password.getText().equals(confirmPassword.getText())){
				if(personal.isSelected()){
					accessorPerson.setUsername(username.getText());
					accessorPerson.setPassword(password.getText());
					controller.addRegisterPersonPane();
				}else if(business.isSelected()){
					accessorEnterprise.setUsername(username.getText());
					accessorEnterprise.setPassword(password.getText());
					controller.addRegisterBusinessPane();
				}else{
					PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
							"注册失败","请选择注册的类型");
					alert.showDialog();
				}
			}else{
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
						"注册失败","请输入相同的密码");
				alert.showDialog();
			}
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"注册失败","请输入你的用户名和密码");
			alert.showDialog();
			
		}
	}
	
	/**
	 * handle the button action (Next)
	 * jump to the login in pane.
	 */
	@FXML
	public void handleBack(){
		
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController centerController) {
		this.controller=centerController;
	}
	
	/**
	 * set the accessor of personal user
	 * @param accessor
	 */
	public void setPersonAccessor(RegisterPersonAccessor accessor) {
		this.accessorPerson=accessor;
	}
	
	/**
	 * set the accessor of business user
	 * @param accessor
	 */
	public void setEnterPriseAccessor(RegisterEnterpriseAccessor accessor) {
		this.accessorEnterprise=accessor;
	}
}
