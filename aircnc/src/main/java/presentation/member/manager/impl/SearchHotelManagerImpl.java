package presentation.member.manager.impl;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.SearchHotelManager;
import presentation.member.model.SearchHotelsModel;
import vo.hotel.HotelVo;

public class SearchHotelManagerImpl implements SearchHotelManager{
	
	private List<HotelVo> hotels;
	
	private ObservableList<SearchHotelsModel> searchHotelsData;
	@Override
	public boolean setHotel(List<HotelVo> list) {
		if(list!=null){
			this.hotels=list;
			return true;
		}
		return false;
	}

	@Override
	public ObservableList<SearchHotelsModel> getHotelList() {
		searchHotelsData = FXCollections.observableArrayList();
		Iterator<HotelVo> it = hotels.iterator();
		while(it.hasNext())
			searchHotelsData.add(new SearchHotelsModel(it.next()));
		return searchHotelsData;
	}

}
