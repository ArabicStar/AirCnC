package service.impl.promotion;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import service.promotion.PromotionContainer;
import utils.info.promotion.PromotionInfo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public abstract class AbstractPromotionContainer implements PromotionContainer {

	protected Set<PromotionVo> all;

	protected AbstractPromotionContainer() {
		all = new HashSet<>();
	}

	@Override
	public Set<PromotionVo> getAll() {
		return new HashSet<>(all);
	}

	@Override
	public Set<PromotionVo> getActive() {
		return all.stream().filter(vo -> vo.getActive()).collect(Collectors.toSet());
	}

	@Override
	public Set<PromotionVo> getPractical() {
		return all.stream().filter(vo -> vo.getPractical()).collect(Collectors.toSet());
	}

	@Override
	public PromotionVo getPromotion(long id) {
		for (PromotionVo vo : all)
			if (vo.getId() == id)
				return vo;

		return null;
	}

	@Override
	public void put(Set<? extends PromotionInfo> info) {
		all.addAll(info.stream().map(i -> new PromotionVoBuilder(i).getPromotionInfo()).collect(Collectors.toSet()));
	}

}
