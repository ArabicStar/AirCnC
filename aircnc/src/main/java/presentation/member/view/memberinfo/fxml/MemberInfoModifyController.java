package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.member.MemberInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;
import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.model.MemberInfoModel;
import presentation.member.utils.dialog.ModifyPasswordDialog;
import presentation.member.utils.dialog.PlainDialog;

public class MemberInfoModifyController implements Initializable {

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

	@FXML
	private Button modifyPassword;

	private ClientCenterController controller;

	private InfoModifyAccessor accessor;

	private UserInfoManager manager;

	private MemberInfoModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model = manager.getMemberInfo();
				id.setText(model.getID());
				name.setText(model.getUsername());
				tele.setText(model.getTele());
				phone.setText(model.getMobi());
				email.setText(model.getEmail());
			}
		});

		accessor = InfoModifyAccessorImpl.getInstance();
		manager = MemberInfoManagerImpl.getInstance();

	}

	public static final boolean checkEmail(String email) {
		return email != null && email.matches("\\w+@\\w+\\.\\w+");
	}

	public static final boolean checkMobilePhone(String mobilePhone) {
		return mobilePhone != null && mobilePhone.matches("[0-9]{11}");
	}

	public static final boolean checkFixedPhone(String fixedPhone) {
		return fixedPhone != null && fixedPhone.matches("[0-9]{3,4}-[0-9]{8}");
	}

	@FXML
	public void handleBack() {
		controller.addInfoMainPane();
	}

	@FXML
	public void handleConfirm() {
		if (name.getText().length() > 0) {
			if (!name.getText().equals(model.getUsername()) || !tele.getText().equals(model.getTele())
					|| !phone.getText().equals(model.getMobi()) || !email.getText().equals(model.getEmail())) {
				if(checkEmail(email.getText())&&checkMobilePhone(phone.getText())&&checkFixedPhone(tele.getText())){
				accessor.setName(name.getText());
				accessor.setMobi(phone.getText());
				accessor.setEmail(email.getText());
				accessor.setTele(tele.getText());
				MemberInfoCourier.getInstance().updateInfo();
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "保存成功", "已保存修改的信息");
				alert.showDialog();
				controller.addInfoMainPane();
				}else if(!checkEmail(email.getText())){
					PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "保存信息失败", "请输入格式正确的邮箱");
					alert.showDialog();
				}else if(!checkMobilePhone(phone.getText())){
					PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "保存信息失败", "请输入格式正确的手机");
					alert.showDialog();
				}else if(!checkFixedPhone(tele.getText())){
					PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "保存信息失败", "请输入格式正确的电话");
					alert.showDialog();
				}
			}
		} else {
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "保存信息失败", "请输入完整的信息");
			alert.showDialog();
		}

	}

	@FXML
	public void handleModifyPassword() {
		// 这里逻辑处理一下
		@SuppressWarnings("unused")
		ModifyPasswordDialog modifyPassword = new ModifyPasswordDialog();
	}

	/**
	 * set the controller
	 * 
	 * @param controller
	 */
	public void setCenterController(ClientCenterController controller) {
		this.controller = controller;
	}

}
