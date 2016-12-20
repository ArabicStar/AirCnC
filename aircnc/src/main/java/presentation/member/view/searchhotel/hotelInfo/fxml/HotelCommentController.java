package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.member.model.CommentModel;

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
	private Label memberName;
	
	@FXML
	private Label grade;
	
	@FXML
	private ImageView portrait;
	
	private CommentModel model;
	
	@SuppressWarnings("unused")
	private HotelInfoThreeController controller;

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
		memberName.setText(String.valueOf(model.getMemberName()));

	}
	
	/**
	 * this model contains all the information presented.
	 * @param model
	 */
	public void setCommentModel(CommentModel model){
		this.model = model;
	}
	
	/**
	 * set the main controller
	 * @param controller
	 */
	public void setController(HotelInfoThreeController controller){
		this.controller = controller;
	}
}
