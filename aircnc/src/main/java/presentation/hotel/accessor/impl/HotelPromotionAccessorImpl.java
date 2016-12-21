package presentation.hotel.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.hotel.accessor.HotelPromotionAccessor;
import vo.promotion.PromotionVo;


public class HotelPromotionAccessorImpl implements HotelPromotionAccessor{
	private static HotelPromotionAccessorImpl instance;
	private long promotionId;
	private boolean practical;
	
	public static final HotelPromotionAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionAccessorImpl();
	}

	public static final HotelPromotionAccessor getInstance() {
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
	public void setPractical(long promotionId, boolean practical) {
		this.promotionId = promotionId;
		this.practical = practical;
		
	}

	@Override
	public void setNewPromotion(long promotionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDeletePromotion(long promotionId) {
		this.promotionId = promotionId;
	}

	@Override
	public long getPromotionId() {
		return promotionId;
	}

	@Override
	public PromotionVo getNewPromotion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPractical() {
		return practical;
	}
}
