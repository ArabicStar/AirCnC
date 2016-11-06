package data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.dao.HotelDao;
import data.datahelper.DataFactory;
import data.datahelper.HotelDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.HotelPo;

public class HotelDaoImpl implements HotelDao{

	private Map<String, HotelPo> map;
	
	private HotelDataHelper hotelDataHelper;
	
	private DataFactory dataFactory;
	
	private static HotelDaoImpl hotelDataServiceImpl;
	
	public static HotelDaoImpl getInstance(){
		if(hotelDataServiceImpl == null){
			hotelDataServiceImpl = new HotelDaoImpl();
		}
		return hotelDataServiceImpl;
	}
	
	public HotelDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelDataHelper = dataFactory.getHotelDataHelper();
			map = hotelDataHelper.getHotelData();
		}
	}
	
	@Override
	public HotelPo getHotel(int hotelId) {
		// TODO Auto-generated method stub
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelDataHelper = dataFactory.getHotelDataHelper();
			map = hotelDataHelper.getHotelData();
		}
		return null;
	}

	@Override
	public List<HotelPo> getHotels(String scope) {
		// TODO Auto-generated method stub
		List<HotelPo> hotelList = new ArrayList<HotelPo>();
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, HotelPo> entry = iterator.next();
			HotelPo hotelPo = entry.getValue();
			if(hotelPo.getScope() == scope){
				hotelList.add(hotelPo);
			}
		}
		return hotelList;
	}

	@Override
	public boolean updateHotel(HotelPo hotelPo) {
		String hotelId = hotelPo.getId();
		if(map.get(hotelId) != null){
			map.put(hotelId,hotelPo);
			hotelDataHelper.updateHotelData(map);
			return true;
		}
		return false;
	}

	@Override
	public boolean addHotelPo(HotelPo hotelPo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteHotelPo(int hotelId) {
		// TODO Auto-generated method stub
		return false;
	}

}
