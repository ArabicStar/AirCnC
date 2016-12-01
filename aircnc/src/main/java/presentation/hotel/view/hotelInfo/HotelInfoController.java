package presentation.hotel.view.hotelInfo;

import presentation.hotel.HotelCenterController;

public class HotelInfoController {
	HotelInfoMainPane infoMainPane;
	HotelInfoOnePane onePane;
	HotelInfoTwoPane twoPane;
	HotelInfoThreePane threePane;
	HotelInfoModifyPane modifyPane;
	
	HotelCenterController controller;
	
	public HotelInfoController(HotelCenterController controller){
		this.controller = controller;
	}
	
	public void addHotelInfoPane() {
		clearContent();
//		orderExecutePane = new OrderExecutePane();
//		mainClient.getBorderPane().setCenter(orderExecutePane.getPane());
//		orderExecutePane.getController().setCenterController(this);	
//		orderExecutePane.getController().test();
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
