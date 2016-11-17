package presentation.member.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * the pane of sign in(General)
 * @author ParanoiaSun
 *
 */

public class MemberSignInPane {

	private Pane signInLayout;
	private BorderPane rootLayout;


	public MemberSignInPane(BorderPane rootLayout){
		this.rootLayout = rootLayout;
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MemberStartPane.class.getResource("fxml/MemberSignIn.fxml"));
            loader.load();
			signInLayout = loader.getRoot();

            // Set sign in overview into the center of root layout.
            rootLayout.setCenter(signInLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
