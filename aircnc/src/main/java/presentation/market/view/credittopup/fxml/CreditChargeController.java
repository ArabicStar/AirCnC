package presentation.market.view.credittopup.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.market.MarketServiceCourier;
import interactor.market.MarketServiceInteractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import presentation.market.MarketCenterController;
import presentation.market.accessor.MarketChargeAccessor;
import presentation.market.accessor.impl.MarketChargeAccessorImpl;
import utils.info.member.MemberInfoTemplate;

public class CreditChargeController implements Initializable {

	@FXML
	private TextField userID;

	@FXML
	private TextField money;

	@SuppressWarnings("unused")
	private MarketCenterController controller;
	
	private MarketChargeAccessor accessor;
	
	private MarketServiceInteractor interactor; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accessor = MarketChargeAccessorImpl.getInstance();
		
		interactor = MarketServiceCourier.getInstance();

	}

	@FXML
	public void handleConfirm() {
		if(check()==""){
			accessor.setMemberId(userID.getText());
			accessor.setTopupMoney(Integer.parseInt(money.getText()));
			interactor.creditCharge();
		}
	}

	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}
	
	private String check(){
		try{
			Integer.parseInt(money.getText());
		}catch (Exception e){
			return "请输入整数形式的充值金额";
		}
		
		if(!MemberInfoTemplate.checkID(userID.getText()))
			return "请输入正确的用户账号";
		
		return "";
	}

}
