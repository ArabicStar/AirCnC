package interactor.utils;

import javafx.scene.control.Alert.AlertType;
import presentation.member.utils.dialog.PlainDialog;

public final class AlertHelper {
	private AlertHelper() {
	}

	public static final void alertFail(String title, String content) {
		new PlainDialog(AlertType.ERROR, title, content).showDialog();
	}

	public static final void alertSuccess(String title, String content) {
		new PlainDialog(AlertType.CONFIRMATION, title, content).showDialog();
	}
}
