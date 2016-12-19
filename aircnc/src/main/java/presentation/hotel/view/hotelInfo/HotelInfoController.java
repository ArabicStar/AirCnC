package presentation.hotel.view.hotelInfo;

import presentation.hotel.HotelCenterController;

public class HotelInfoController {
	private HotelInfoMainPane infoMainPane;
	private HotelInfoOnePane onePane;
	private HotelInfoTwoPane twoPane;
	private HotelInfoThreePane threePane;
	
	private HotelCenterController centerController;
	
	public HotelInfoController(HotelInfoMainPane infoMainPane,HotelCenterController centerController){
		this.infoMainPane = infoMainPane;
		this.centerController = centerController;
		addHotelInfoOnePane();
	}
	
	public void addHotelModifyPane() {
		centerController.addHotelModifyPane();
	}
	
	public void addHotelInfoOnePane() {
		clearContent();
		onePane = new HotelInfoOnePane();
		infoMainPane.getBorderPane().setCenter(onePane.getPane());
		onePane.getController().setInfoMainController(this);	
		onePane.getController().setManager(infoMainPane.getController().getManager());
	}
	
	public void addHotelInfoTwoPane() {
		clearContent();
		twoPane = new HotelInfoTwoPane();
		infoMainPane.getBorderPane().setCenter(twoPane.getPane());
		twoPane.getController().setInfoMainController(this);	
	}
	
	public void addHotelInfoThreePane() {
		clearContent();
		threePane = new HotelInfoThreePane();
		infoMainPane.getBorderPane().setCenter(threePane.getPane());
		threePane.getController().setInfoMainController(this);	
	}
	
	
	
	/**
	 * remove all the children nodes of the main border pane, except the
	 * nav-bar.
	 */
	public void clearContent() {
		int childrenAmount = infoMainPane.getBorderPane().getChildren().size();
		if (childrenAmount > 1) {
			for (int i = 1; i < childrenAmount; i++) {
				infoMainPane.getBorderPane().getChildren().remove(i);
			}
		}
	}
}
