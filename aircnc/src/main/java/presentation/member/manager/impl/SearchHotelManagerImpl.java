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
 * the manager of searched hotel info aiming to receive the MemberVo from the
 * logic layer and deliver the member info model to the presentation layer
 * 
 * @author paranoia
 *
 */
public class SearchHotelManagerImpl implements SearchHotelManager {

	private static SearchHotelManager instance;

	private List<HotelVo> hotels;
	boolean only = false;

	public static final SearchHotelManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new SearchHotelManagerImpl();
	}

	public static final SearchHotelManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	public static boolean isLaunched() {
		if (instance == null)
			return false;
		else
			return true;
	}

	private ObservableList<SearchHotelsModel> searchHotelsData = FXCollections.observableArrayList();
	private ObservableList<SearchHotelsModel> searchHotelsOnly = FXCollections.observableArrayList();
	private ObservableList<SearchHotelsModel> usedData = FXCollections.observableArrayList();

	@Override
	public boolean setHotel(List<HotelVo> list) {
		if (list != null) {
			this.hotels = list;
			calData();
			calOnly();
			return true;
		}
		return false;
	}

	public void calData() {
		searchHotelsData.clear();
		Iterator<HotelVo> iter = hotels.iterator();
		while (iter.hasNext()) {
			searchHotelsData.add(new SearchHotelsModel(iter.next()));
		}
	}

	public void calOnly() {
		for (int i = 0; i < searchHotelsData.size(); i++) {
			if (searchHotelsData.get(i).getHistoryOrder().size() > 0)
				searchHotelsOnly.add(searchHotelsData.get(i));
		}
	}

	/**
	 * wrap into the observablelist
	 */
	@Override
	public ObservableList<SearchHotelsModel> getHotelList(int page) {
		usedData.clear();
		if (!only) {
			for (int i = page * 4; i < searchHotelsData.size() && i < (page + 1) * 4; ++i) {
				usedData.add(searchHotelsData.get(i));
			}
		} else {
			for (int i = page * 4; i < searchHotelsOnly.size() && i < (page + 1) * 4; ++i) {
				usedData.add(searchHotelsOnly.get(i));
			}
		}
		return usedData;
	}

	@Override
	public int getSearchedNum() {
		if (!only)
			return searchHotelsData.size();
		else
			return searchHotelsOnly.size();
	}

	@Override
	public ObservableList<SearchHotelsModel> getWholeHotelList() {
		searchHotelsData.clear();
		Iterator<HotelVo> iter = hotels.iterator();
		while (iter.hasNext()) {
			searchHotelsData.add(new SearchHotelsModel(iter.next()));
		}
		return searchHotelsData;
	}

	@Override
	public void setOnlyMe(boolean onlyMe) {
		this.only = true;
	}

}
