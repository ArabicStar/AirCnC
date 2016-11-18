package presentation.member.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import presentation.member.CenterController;
import presentation.member.view.fxml.MemberRegisterMainController;
import javafx.scene.layout.BorderPane;

/**
 * the pane of register(Main)
 * @author ParanoiaSun
 *
 */

public class MemberRegisterMainPane {
	
	private BorderPane rootLayout;
	private Pane registerLayout;
	private MemberRegisterMainController controller;
	private CenterController centerController;
	
	public MemberRegisterMainPane(BorderPane rootLayout,CenterController centerController){
		this.rootLayout = rootLayout;
		this.centerController = centerController;
		init();
	}
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MemberStartPane.class.getResource("fxml/MemberRegisterMain.fxml"));
            controller.setCenterController(centerController);
            controller = loader.getController();
            loader.load();
			registerLayout = loader.getRoot();

            // Set sign in overview into the center of root layout.
            rootLayout.setCenter(registerLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
