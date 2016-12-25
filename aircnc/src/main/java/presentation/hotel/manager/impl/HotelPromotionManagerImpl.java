package presentation.hotel.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.hotel.manager.HotelPromotionManager;
import presentation.hotel.model.HotelPromotionModel;
import vo.promotion.PromotionVo;

public class HotelPromotionManagerImpl implements HotelPromotionManager{
	private static HotelPromotionManagerImpl instance;
	
	private Set<PromotionVo> promotions;
	
	private ObservableList<HotelPromotionModel> promotionData;
	
	private int hotelId;

	
	public static final HotelPromotionManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionManagerImpl();
	}
	
	public static final HotelPromotionManager getInstance(){
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
	
	@Override
	public boolean setPromotion(Set<PromotionVo> promotions) {
		if(promotions!=null){
			this.promotions=promotions;
			return true;
		}
		return false;
		
	}

	@Override
	public List<String> getDescription() {
		
		return promotions.stream().filter(p->p.getPractical()).map(PromotionVo::description).
				collect(Collectors.toList());
	}
	
	@Override
	public ObservableList<HotelPromotionModel> getPromotionList() {
		promotionData = FXCollections.observableArrayList();
//		test();
		Iterator<PromotionVo> it = promotions.iterator();
		while(it.hasNext())
			promotionData.add(new HotelPromotionModel(it.next()));
		return promotionData;
	}

	@Override
	public void setHotelId(int id) {
		this.hotelId = id;
		
	}

	@Override
	public int getHotelId() {

		return hotelId;
	}
	

	

}
