package presentation.member.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

/**
 * the pane of register(Main)
 * @author ParanoiaSun
 *
 */

public class MemberRegisterMainPane {
	
	private BorderPane rootLayout;
	private Pane registerLayout;
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MemberStartPane.class.getResource("fxml/MemberRegisterMain.fxml"));
            loader.load();
			registerLayout = loader.getRoot();

            // Set sign in overview into the center of root layout.
            rootLayout.setCenter(registerLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
