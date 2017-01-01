package presentation.market.view.levelStrategy;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.levelStrategy.fxml.LevelStrategyController;

public class LevelStrategyPane {
	private Pane orderLayout;
	private LevelStrategyController controller;
	
	public LevelStrategyPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/LevelStrategy.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (LevelStrategyController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LevelStrategyController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
