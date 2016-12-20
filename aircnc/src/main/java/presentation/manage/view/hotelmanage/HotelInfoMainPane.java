package presentation.manage.view.hotelmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import presentation.manage.model.HotelManageModel;
import presentation.manage.view.hotelmanage.fxml.HotelInfoMainController;

public class HotelInfoMainPane {
	private AnchorPane rootLayout;
	private HotelInfoMainController controller;
	private HotelManageModel model;
	
	public HotelInfoMainPane(HotelManageModel model){
		this.model = model;
		initRootLayout();
	}

	public void initRootLayout() {
        try {
        	// Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelInfoMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = loader.getController();
			rootLayout = loader.getRoot();
			controller.setModel(model);
			controller.setInfoMainPane(rootLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public HotelInfoMainController getController(){
		return controller;
	}
    
    public AnchorPane getAnchorPane() {
        return rootLayout;
    }
    
//    public Node getPane(){
//    	return rootLayout;
//    }
}
