package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import interactor.impl.order.OrderInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import presentation.member.accessor.MemberCommentAccessor;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;
import vo.order.OrderVo;

public class MemberCommentController implements Initializable {

	@FXML
	private GridPane gridPane;

	private Rating rates;

	@FXML
	private TextArea comment;

	@FXML
	private Button confirm;

	private OrderVo vo;
	private MemberCommentAccessor accessor;
	private MemberOrderMainController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rates = new Rating();
		gridPane.add(rates, 0, 1);
		rates.setPadding(new Insets(0, 0, 0, 80));
		rates.setRating(0);
		confirm.setDisable(true);
		comment.setPrefColumnCount(8);
		comment.setPrefRowCount(5);
		Platform.runLater(() -> {
			// 监听键入,输入的除去空格后有内容则恢复按钮
			comment.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
		accessor = MemberCommentAccessorImpl.getInstance();
	}

	@FXML
	public void handleConfirm() {
		if (rates.getRating() != 0) {
			if (comment.getText().length() < 50) {
				accessor.setComment(comment.getText());
				accessor.setRating(rates.getRating());
				accessor.setOrderId(vo.getOrderId());
				OrderInfoCourier.getInstance().makeComment();
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "评价成功", "感谢您的评价！");
				alert.showDialog();
				controller.removeCommentPane();
			} else {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "评价失败", "评论请少于50字");
				alert.showDialog();
			}
		} else {
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "评价失败", "请为酒店评分");
			alert.showDialog();
		}
	}

	@FXML
	public void handleClose() {
		controller.removeCommentPane();
	}

	public void setOrderVo(OrderVo vo) {
		this.vo = vo;
	}

	public void setController(MemberOrderMainController controller) {
		this.controller = controller;
	}
}
