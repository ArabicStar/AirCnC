package presentation.manage.view.marketmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import presentation.manage.view.marketmanage.fxml.MarketAddController;

public class MarketAddPane {
	private AnchorPane searchLayout;
	private MarketAddController controller;

	public MarketAddPane(){
		init();
	}

	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/MarketAdd.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = loader.getController();
			searchLayout = loader.getRoot();

			DropShadow ds = new DropShadow();

			ds.setOffsetY(5.0);
			ds.setOffsetX(5.0);
			ds.setColor(Color.GRAY);

			searchLayout.setEffect(ds);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MarketAddController getController() {
		return this.controller;
	}

	public Node getPane() {
		return searchLayout;
	}
}
