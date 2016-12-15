package presentation.member.view.searchhotel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import presentation.member.model.SearchHotelsModel;
import presentation.member.view.searchhotel.fxml.MemberSearchHotelGeneralController;

/**
 * the pane of hotel search
 * @author paranoia
 *
 */
public class MemberSearchHotelGeneralPane {
	
	private AnchorPane searchLayout;
	private MemberSearchHotelGeneralController controller;
	private SearchHotelsModel model;

	public MemberSearchHotelGeneralPane(SearchHotelsModel model){
		this.model = model;
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberHotelGeneral.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (MemberSearchHotelGeneralController)loader.getController();
            controller.setHotelModel(model);
            searchLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberSearchHotelGeneralController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return searchLayout;
    }
    
}
