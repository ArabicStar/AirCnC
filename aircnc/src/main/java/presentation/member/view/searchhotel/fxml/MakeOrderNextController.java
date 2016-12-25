package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.manager.impl.MakeOrderManagerImpl;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public class MakeOrderNextController implements Initializable {

	@FXML
	private Label promotionDes;

	@FXML
	private Label totalPrice;

	@FXML
	private Label disPrice;

	private OrderVo order;
	private Set<PromotionVo> promotions;
	private MemberSearchHotelGeneralController parentController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		order = MakeOrderManagerImpl.getInstance().getOrderVo();
		this.promotions = order.getPromotions();
		Iterator<PromotionVo> it = promotions.iterator();
		String des = "";
		int index = 1;

		while (it.hasNext()) {
			des += String.valueOf(index) + "." + it.next().description() + "\n";
			index++;
		}

		this.promotionDes.setText(des);
		this.totalPrice.setText(String.valueOf(order.getOriginalPrice()));
		this.disPrice.setText(String.valueOf(order.getDiscountPrice()) + "元");

	}

	@FXML
	public void handleConfirm() {
		OrderMakerAccessorImpl.getIntance().setCompleteOrder(order);
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
