package presentation.market.view.credittopup.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.market.MarketCenterController;

public class CreditTopUpController implements Initializable {

	@FXML
	private Button comfirm;

	@FXML
	private TextField userID;

	@FXML
	private TextField moneyToppedUp;

	@SuppressWarnings("unused")
	private MarketCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void handleConfirm() {
		System.out.println("可以点击确认");
	}

	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}

}
