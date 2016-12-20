package presentation.manage.view.hotelmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.manage.model.ManageCommentModel;
import presentation.manage.view.hotelmanage.fxml.HotelCommentController;

public class HotelCommentPane {
	private Pane layout;
	private HotelCommentController controller;
	private ManageCommentModel model;

	public HotelCommentPane(ManageCommentModel model){
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
