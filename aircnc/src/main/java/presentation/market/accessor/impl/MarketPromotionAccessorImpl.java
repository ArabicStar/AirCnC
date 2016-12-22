package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.market.accessor.MarketPromotionAccessor;
import vo.promotion.PromotionVo;

public class MarketPromotionAccessorImpl implements MarketPromotionAccessor {
	private static MarketPromotionAccessor instance;

	private PromotionVo promotion;

	private long promotionId;

	private boolean practical;

	public static MarketPromotionAccessor launch() {
		if (instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new MarketPromotionAccessorImpl();
	}

	public static MarketPromotionAccessor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}

	public static boolean isLaunched() {
		if (instance == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void setPractical(long promotionId, boolean practical) {
		this.promotionId = promotionId;
		this.practical = practical;
		
	}

	@Override
	public void setPromotion(PromotionVo vo) {
		//if vo has id, update; else add;
		this.promotion = vo;
		
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
	public PromotionVo getPromotion() {
		return promotion;
	}

	@Override
	public boolean getPractical() {
		return practical;
	}
}
