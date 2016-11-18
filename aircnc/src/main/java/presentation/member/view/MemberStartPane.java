package presentation.member.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * the pane of sign in(scene)
 * @author ParanoiaSun
 *
 */

public class MemberStartPane extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	private MemberSignInPane memberSignIn;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        //this.primaryStage.setTitle("AddressApp");  这里的标题写在main里面

        initRootLayout();

        memberSignIn = new MemberSignInPane(rootLayout);

	}

	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MemberStartPane.class.getResource("fxml/MemberStart.fxml"));
            rootLayout = (BorderPane) loader.load();
//            primaryStage.initStyle(StageStyle.UNDECORATED);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout,550,385);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AirCnC");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args){
		launch(args);
	}

}
