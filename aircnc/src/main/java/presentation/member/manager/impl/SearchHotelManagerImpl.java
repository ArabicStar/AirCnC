package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.SearchHotelManager;
import presentation.member.model.SearchHotelsModel;
import vo.hotel.HotelVo;

/**
 * the manager of member info
 * aiming to receive the MemberVo from the logic layer
 * and deliver the member info model to the presentation layer
 * @author paranoia
 *
 */
public class SearchHotelManagerImpl implements SearchHotelManager{
	
	private static SearchHotelManager instance;
	
	private List<HotelVo> hotels;
	
	public static final SearchHotelManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SearchHotelManagerImpl();
	}
	
	public static final SearchHotelManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	public static boolean isLaunched(){
		if(instance == null)
			return false;
		else
			return true;
	}
	
	private ObservableList<SearchHotelsModel> searchHotelsData;
	@Override
	public boolean setHotel(List<HotelVo> list) {
		if(list!=null){
			this.hotels=list;
			return true;
		}
		return false;
	}
	
	/**
	 * wrap into the observablelist
	 */
	@Override
	public ObservableList<SearchHotelsModel> getHotelList() {
		searchHotelsData = FXCollections.observableArrayList();
		Iterator<HotelVo> it = hotels.iterator();
		while(it.hasNext())
			searchHotelsData.add(new SearchHotelsModel(it.next()));
		return searchHotelsData;
	}

}
