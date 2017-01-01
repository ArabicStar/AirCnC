package presentation.member.utils.cell;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import presentation.member.model.CreditModel;

/**
 * CreditChangeCell use a label(num) to show the num of change. use ImageView to
 * show whether it is up or down. and wrapped in a hbox.
 * 
 * @author paranoia
 *
 */
public class CreditChangeCell extends TableCell<CreditModel, Integer> {

	final HBox change;
	final Label num;
	ImageView state;
	Image image;

	/**
	 * create the creditChangeCell
	 * 
	 * @param changeNum
	 */
	public CreditChangeCell(int changeNum) {

		if (changeNum > 0) {
			//image = new Image("../../../../../images/member/credit-up.png");
			//"../../../../../../resources/images/member/credit-up.png"
			num = new Label("+" + String.valueOf(changeNum));
		} else {
			num = new Label(String.valueOf(changeNum));
			//image = new Image("../../../../../images/member/credit-down.png");
		}

		//state = new ImageView(image);
		change = new HBox();
		//change.getChildren().add(state);
		change.getChildren().add(num);
	}

	/**
	 * Display the HBox if the row is not empty
	 * 
	 * @param status
	 * @param empty
	 */
	@Override
	protected void updateItem(Integer changeNum, boolean empty) {
		super.updateItem(changeNum, empty);
		if (!empty) {
			setGraphic(change);
		}else{
			setGraphic(null);
		}
	}
}
