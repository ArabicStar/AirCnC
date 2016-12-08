package presentation.market.view.abnormalorderbrowse;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.abnormalorderbrowse.fxml.AbnormalOrderBrowseController;

public class AbnormalOrderBrowsePane {
	private Pane orderLayout;
	private AbnormalOrderBrowseController controller;
	
	public AbnormalOrderBrowsePane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/AbnormalOrderBrowse.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			// javafx.scene.Parent root = (javafx.scene.Parent)
			// loader.load(location.openStream());
			loader.load();
			controller = (AbnormalOrderBrowseController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AbnormalOrderBrowseController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
