package presentation.manage.utils.dialog;

import java.util.Optional;

import interactor.impl.manage.ManageMarketCourier;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;
import vo.market.MarketVo;

public class MarketModifyDialog {

	public MarketModifyDialog(MarketVo vo) {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("修改营销人员信息");
		dialog.setHeaderText("填写修改的信息");

		ButtonType ConfirmButtonType = new ButtonType("确认", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ConfirmButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField newName = new TextField();
		newName.setText(vo.getName());

		grid.add(new Label("营销人员名称:"), 0, 0);
		grid.add(newName, 1, 0);

		Node confirmButton = dialog.getDialogPane().lookupButton(ConfirmButtonType);
		confirmButton.setDisable(true);

		newName.textProperty().addListener((observable, oldValue, newValue) -> {
			confirmButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ConfirmButtonType) {
				return newName.getText();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(newInfo -> {
			MarketManageInfoAccessorImpl.getInstance().setMarketVo(vo);
			MarketManageInfoAccessorImpl.getInstance().setName(newName.getText());
			if (ManageMarketCourier.getInstance().ModifyMarketInfo()) {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改成功", "已成功修改营销人员信息");
				alert.showDialog();
			} else {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改失败", "修改营销人员信息失败");
				alert.showDialog();
			}
		});
	}

}
