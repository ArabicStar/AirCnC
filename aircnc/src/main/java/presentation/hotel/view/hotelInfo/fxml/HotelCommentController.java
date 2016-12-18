package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.hotel.model.CommentModel;

public class HotelCommentController implements Initializable{
	@FXML
	private Label level;
	
	@FXML
	private Label checkInTime;
	
	@FXML
	private Label commentTime;
	
	@FXML
	private Label content;
	
	@FXML
	private Label memberId;
	
	@FXML
	private Label grade;
	
	@FXML
	private ImageView portrait;
	
	private CommentModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initCommentInfo();
			  }
		});
		
	}
	
	private void initCommentInfo(){

		URL loc = getClass().getResource("/images/hotel/member-portrait.png");
		Image image = new Image(loc.toString()); 
		portrait.setImage(image);
		level.setText(model.getLevel());
		checkInTime.setText(model.getCheckInTime());
		commentTime.setText(model.getCommentTime());
		content.setText(model.getContent());
		grade.setText(model.getGrade());
		memberId.setText(String.valueOf(model.getMemberId()));

	}
	
	/**
	 * this model contains all the information presented.
	 * @param model
	 */
	public void setCommentModel(CommentModel model){
		this.model = model;
	}
}
