package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import presentation.member.accessor.MemberOrderOperationAccessor;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;
import vo.order.OrderVo;

public class MemberAppealController implements Initializable{
	
	@FXML
	private TextArea appeal;
	
	@FXML
	private Button confirm;
	
	private OrderVo vo;
	private MemberOrderOperationAccessor accessor;
	private MemberOrderMainController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		confirm.setDisable(true);
		Platform.runLater(() -> {
			// 监听键入,输入的除去空格后有内容则恢复按钮
			appeal.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
		accessor = MemberOrderOperationAccessorImpl.getInstance();
	}
	
	@FXML
	public void handleConfirm(){
		accessor.setAppeal(vo, appeal.getText());
		PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
			"申诉成功","已经收到您的申诉");
		//OrderOperationCourier.getInstance().
		alert.showDialog();
		controller.removeAppealPane();
	}
	
	@FXML
	public void handleClose(){
		controller.removeAppealPane();
	}

	public void setOrderVo(OrderVo vo){
		this.vo = vo;
	}
	
	public void setController(MemberOrderMainController controller){
		this.controller=controller;
	}
	
}
