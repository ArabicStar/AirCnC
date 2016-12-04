package presentation.hotel.view.abnormalOrder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.hotel.view.abnormalOrder.fxml.AbnormalOrderController;

public class AbnormalOrderPane {
	private Pane orderLayout;
	private AbnormalOrderController controller;


	public AbnormalOrderPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/AbnormalOrder.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (AbnormalOrderController)loader.getController();
            orderLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AbnormalOrderController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
