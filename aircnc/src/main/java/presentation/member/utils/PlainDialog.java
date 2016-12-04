package presentation.member.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PlainDialog {
	
	private Alert alert;
	
	public PlainDialog(AlertType type,String title,String content){
		alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
	}
	
	public void showDialog(){
		alert.showAndWait();
	}
}
