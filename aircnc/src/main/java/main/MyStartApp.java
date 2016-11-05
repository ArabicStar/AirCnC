package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * TODO: 这是用来测试界面跳转的
 * @author Water
 *
 */
public class MyStartApp extends Application{
	public static void main(String[] args) {
		Application.launch();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setWidth(800);
		stage.setHeight(600);
		Label nameLbl = new Label("Enter your name:");
		TextField nameFld = new TextField();
		
		Label msg = new Label();
		msg.setStyle("-fx-text-fill: blue;");
		
		Button sayHelloBtn = new Button("Say Hello");
		Button exitBtn = new Button("Exit");
		
		sayHelloBtn.setOnAction(e -> {
			String name = nameFld.getText();
			if(name.trim().length() > 0) {
				msg.setText("Hello " + name);
			} else {
				msg.setText("Hello there");
			}
		});
		
		/**
		 * 这里测试了一下界面的跳转部分，目测之后要改成fxml
		 */
		exitBtn.setOnAction(e -> {
			VBox newVbox = new VBox();
			Label newMsg = new Label();
			newMsg.setText("界面已经跳转了！！！只有聪明人才能看到！！！");
			newVbox.setSpacing(5);
			newVbox.getChildren().addAll(nameLbl, nameFld, sayHelloBtn, newMsg, msg);
			stage.setScene(new Scene(newVbox, 200, 200));
			stage.setTitle("嘿嘿");
		});
		
		VBox root = new VBox();
		
		root.setSpacing(5);
		
		root.getChildren().addAll(nameLbl, nameFld, msg, sayHelloBtn, exitBtn);
		
		Scene scene = new Scene(root, 350, 150);
		stage.setScene(scene);
		stage.setTitle("AirCnC");
		stage.show();
	}
	


}
