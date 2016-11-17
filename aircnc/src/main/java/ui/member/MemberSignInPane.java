package ui.member;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberSignInPane extends Application{

		final static int Login_width = 550;
		final static int Login_height = 385;

		@Override
		public void start(Stage primaryStage) throws Exception {
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("login.fxml"));
				loader.load();
				Pane pane = loader.getRoot();

				LoginController c =(LoginController)loader.getController();
				primaryStage.initStyle(StageStyle.UNDECORATED);
				//c.setStage(primaryStage);

				//下面这个语句可以改我们小应用的图~标~哦~
				//primaryStage.getIcons().add(new Image("file:images/login_BG.png"));

				Font Deng = Font.loadFont("file:fonts/Dengl.ttf", 20);
				/*Text buttonContent = new Text("登录");*/

	            Scene scene = new Scene(pane,Login_width, Login_height);
	            scene.getStylesheets().add(getClass().getResource("login.css") .toExternalForm());

	            primaryStage.setScene(scene);
	            primaryStage.setTitle("My First Chat App");
	            primaryStage.setResizable(false);
	            primaryStage.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		public static void main(String[] args){
			launch(args);
		}
	}
