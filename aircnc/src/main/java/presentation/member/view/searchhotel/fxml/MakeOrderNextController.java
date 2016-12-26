package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.manager.impl.MakeOrderManagerImpl;
import utils.info.order.OrderInfo;
import utils.info.promotion.PromotionInfo;
import vo.order.OrderVoBuilder;

public class MakeOrderNextController implements Initializable {

	@FXML
	private Label promotionDes;

	@FXML
	private Label totalPrice;

	@FXML
	private Label disPrice;

	private OrderInfo order;
	private Set<? extends PromotionInfo> promotions;
	private MemberSearchHotelGeneralController parentController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			  initMakeOrderNext();
		});	

	}
	
	public void initMakeOrderNext(){
		order = MakeOrderManagerImpl.getInstance().getOrderVo();
		this.promotions =  order.getPromotions();
		Iterator<? extends PromotionInfo> it = promotions.iterator();
		String des = "";
		int index = 1;

		while (it.hasNext()) {
			des += String.valueOf(index) + "." + it.next().getDescription() + "\n";
			index++;
		}

		this.promotionDes.setText(des);
		this.totalPrice.setText(String.valueOf(order.getOriginalPrice())+"元");
		this.disPrice.setText(String.valueOf(order.getDiscountPrice()) + "元");
	}

	@FXML
	public void handleConfirm() {
		OrderMakerAccessorImpl.getIntance().setCompleteOrder(new OrderVoBuilder(order).getOrderInfo());
		this.parentController.removeReverse();
	}

	@FXML
	public void handleClose() {
		this.parentController.removeReverse();
	}

	public void setParentController(MemberSearchHotelGeneralController controller) {
		this.parentController = controller;
	}

}
