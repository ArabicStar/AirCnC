package presentation.market.view.websitePromotion;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.websitePromotion.fxml.WebsitePromotionController;

public class WebsitePromotionPane {
	private Pane orderLayout;
	private WebsitePromotionController controller;
	
	public WebsitePromotionPane() {
		init();
	}

	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/WebsitePromotion.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (WebsitePromotionController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebsitePromotionController getController() {
		return this.controller;
	}

	public Pane getPane() {
		return orderLayout;
	}

}
