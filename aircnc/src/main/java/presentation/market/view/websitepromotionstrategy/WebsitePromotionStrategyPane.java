package presentation.market.view.websitepromotionstrategy;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.websitepromotionstrategy.fxml.WebsitePromotionStrategyController;

public class WebsitePromotionStrategyPane {
	private Pane orderLayout;
	private WebsitePromotionStrategyController controller;
	
	public WebsitePromotionStrategyPane() {
		init();
	}

	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/WebsitePromotionStrategy.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (WebsitePromotionStrategyController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebsitePromotionStrategyController getController() {
		return this.controller;
	}

	public Pane getPane() {
		return orderLayout;
	}

}
