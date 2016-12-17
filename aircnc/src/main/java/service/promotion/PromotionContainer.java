package service.promotion;

import java.util.Set;

import utils.info.promotion.PromotionInfo;
import vo.promotion.PromotionVo;

public interface PromotionContainer {

	void put(Set<? extends PromotionInfo> info);

	Set<PromotionVo> getAll();

	Set<PromotionVo> getActive();

	Set<PromotionVo> getPractical();

	PromotionVo getPromotion(long id);

	void refreshContainer();
}