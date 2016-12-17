package presentation.member.view.myorder.fxml;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import presentation.member.accessor.MemberAppealAccessor;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import vo.order.OrderVo;

public class MemberAppealController implements Initializable{
	
	@FXML
	private Label close;
	
	@FXML
	private TextArea comment;
	
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
			comment.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
		accessor = MemberAppealAccessorImpl.getInstance();
	}

	public void setOrderVo(OrderVo vo){
		this.vo = vo;
	}
	
	public void setController(MemberOrderMainController controller){
		this.controller=controller;
	}
	
}
