package presentation.member.view.searchhotel.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.searchhotel.hotelInfo.fxml.HotelInfoMainController;

public class HotelInfoMainPane {
	private AnchorPane rootLayout;
	private HotelInfoMainController controller;
	private SearchHotelsModel model;
	
	public HotelInfoMainPane(SearchHotelsModel model){
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
