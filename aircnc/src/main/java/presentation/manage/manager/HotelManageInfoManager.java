package presentation.manage.manager;

import javafx.collections.ObservableList;
import presentation.manage.model.HotelManageModel;
import vo.hotel.HotelVo;

public interface HotelManageInfoManager {
	
	public boolean setHotel(HotelVo vo);
	
	public ObservableList<HotelManageModel> getHotelInfo();
	
}
