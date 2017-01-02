package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import presentation.member.ClientCenterController;
import presentation.member.utils.dialog.PlainDialog;

/**
 * the controller of member main pane.
 * 
 * @author paranoia
 *
 */
public class MemberMainController implements Initializable {

	@FXML
	private Label memberInfo;

	@FXML
	private Label searchHotel;

	@FXML
	private Label myOrder;

	@FXML
	private Label creditChange;

	@FXML
	private Button logout;

	private ClientCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void HandleLogout() {
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION, "注销确认", "确认登出吗？");

		Optional<ButtonType> result = alert.showDialog();
		if (result.get() == ButtonType.OK) {
			controller.initializeLogin();

		}
	}

	@FXML
	private void HandleMemberInfo() {
		memberInfo.setDisable(true);
		searchHotel.setDisable(false);
		myOrder.setDisable(false);
		creditChange.setDisable(false);
		controller.addInfoMainPane();
	}

	@FXML
	private void HandleSearchHotel() {
		memberInfo.setDisable(false);
		searchHotel.setDisable(true);
		myOrder.setDisable(false);
		creditChange.setDisable(false);
		controller.addSearchHotelPane();
	}

	@FXML
	private void HandleMyOrder() {
		memberInfo.setDisable(false);
		searchHotel.setDisable(false);
		myOrder.setDisable(true);
		creditChange.setDisable(false);
		controller.addOrderMainPane();
	}

	@FXML
	private void HandleCreditChange() {
		memberInfo.setDisable(false);
		searchHotel.setDisable(false);
		myOrder.setDisable(false);
		creditChange.setDisable(true);
		controller.addCreditChangePane();
	}

	public void setSearchHotelDisable(boolean b) {
		searchHotel.setDisable(b);
	}

	@FXML
	public void handleCloseWindow() {
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION, "退出客户端", "确认退出吗？");

		Optional<ButtonType> result = alert.showDialog();
		if (result.get() == ButtonType.OK) {
			controller.closeWindow();

		}
	}

	/**
	 * set the centerController
	 * 
	 * @param centerController
	 */
	public void setCenterController(ClientCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller = centerController;
	}
}
