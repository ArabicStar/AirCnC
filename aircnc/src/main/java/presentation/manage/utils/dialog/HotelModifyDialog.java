package presentation.manage.utils.dialog;

import java.util.Optional;

import interactor.impl.manage.ManageHotelCourier;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;
import vo.hotel.HotelVo;

public class HotelModifyDialog {

	public HotelModifyDialog(HotelVo vo) {
		// Create the custom dialog.
		Dialog<Pair<String, Integer>> dialog = new Dialog<>();
		dialog.setTitle("修改酒店信息");
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
		ComboBox<Integer> newStar = new ComboBox<Integer>();
		newStar.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
		newStar.setValue(Integer.valueOf(vo.getStar()));

		grid.add(new Label("酒店名称:"), 0, 0);
		grid.add(newName, 1, 0);
		grid.add(new Label("修改星级:"), 0, 1);
		grid.add(newStar, 1, 1);

		Node confirmButton = dialog.getDialogPane().lookupButton(ConfirmButtonType);
		confirmButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		newName.textProperty().addListener((observable, oldValue, newValue) -> {
			confirmButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ConfirmButtonType) {
				return new Pair<>(newName.getText(), newStar.getValue());
			}
			return null;
		});

		Optional<Pair<String, Integer>> result = dialog.showAndWait();

		result.ifPresent(newInfo -> {
			HotelManageInfoAccessorImpl.getInstance().setHotelVo(vo);
			HotelManageInfoAccessorImpl.getInstance().setName(newName.getText());
			HotelManageInfoAccessorImpl.getInstance().setStar(newStar.getValue());
			HotelManageInfoAccessorImpl.getInstance().setId(vo.getId());
			if (ManageHotelCourier.getInstance().ModifyHotelInfo()) {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改成功", "已成功修改酒店信息");
				alert.showDialog();
			} else {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改失败", "修改酒店信息失败");
				alert.showDialog();
			}
		});
	}

}
