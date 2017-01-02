package presentation.member.manager;

import java.util.List;

import javafx.collections.ObservableList;
import presentation.member.model.SearchHotelsModel;
import vo.hotel.HotelVo;

public interface SearchHotelManager {
	
	public boolean setHotel(List<HotelVo> list);
	
	public ObservableList<SearchHotelsModel> getHotelList(int pageNum);
	
	public int getSearchedNum();
	
}
