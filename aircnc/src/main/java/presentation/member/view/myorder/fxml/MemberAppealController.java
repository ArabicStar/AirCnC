package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.order.OrderInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import presentation.member.accessor.MemberAppealAccessor;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;
import vo.order.OrderVo;

public class MemberAppealController implements Initializable{
	
	@FXML
	private TextArea appeal;
	
	@FXML
	private Button confirm;
	
	private OrderVo vo;
	private MemberAppealAccessor accessor;
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
		accessor = MemberAppealAccessorImpl.getInstance();
	}
	
	@FXML
	public void handleConfirm(){
		accessor.setAppeal(appeal.getText());
		accessor.setOrderId(vo.getOrderId());
		PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
			"申诉成功","已经收到您的申诉");
		OrderInfoCourier.getInstance().makeAppeal();
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
