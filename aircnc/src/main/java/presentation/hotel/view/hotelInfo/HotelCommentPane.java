package presentation.hotel.view.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.hotel.model.CommentModel;

public class HotelCommentPane {
	private Pane infoThreeLayout;
//	private MemberSearchHotelGeneralController controller;
	private CommentModel model;

	public HotelCommentPane(CommentModel model){
		this.model = model;
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelComment.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
//            controller = (MemberSearchHotelGeneralController)loader.getController();
//            controller.setHotelModel(model);
//            searchLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public MemberSearchHotelGeneralController getController(){
//    	return this.controller;
//    }
    
    public Node getPane(){
    	return infoThreeLayout;
    }
}
