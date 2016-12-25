package presentation.market.utils.cell;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class PlainDialog {
	private Alert alert;
	
	public PlainDialog(AlertType type,String title,String content){
		alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.getDialogPane().setStyle("-fx-background-color: #fff;"+"-fx-border-width:2px;"
				+"-fx-border-color:#4f4e84;");
		alert.initStyle(StageStyle.UNDECORATED);
	}
	
	public void showDialog(){
		alert.showAndWait();
	}
}
