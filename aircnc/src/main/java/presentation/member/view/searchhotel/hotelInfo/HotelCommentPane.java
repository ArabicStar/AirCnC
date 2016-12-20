package presentation.member.view.searchhotel.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.model.CommentModel;
import presentation.member.view.searchhotel.hotelInfo.fxml.HotelCommentController;

public class HotelCommentPane {
	private Pane layout;
	private HotelCommentController controller;
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
            controller = (HotelCommentController)loader.getController();
            controller.setCommentModel(model);
            layout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelCommentController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return layout;
    }
}
