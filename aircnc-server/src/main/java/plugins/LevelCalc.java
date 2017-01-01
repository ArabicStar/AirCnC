package plugins;

import data.dao.impl.market.MarketDaoImpl;
import data.dao.market.MarketDao;
import po.member.MemberPo;
import utils.info.level.LevelStrategy;

public final class LevelCalc {
	private static final MarketDao dao = MarketDaoImpl.INSTANCE;
	private static LevelStrategy level = dao.getLevelStrategy();

	public static final MemberPo calcLevel(MemberPo po) {
		return po.setLevel(level.calcLevel(po.getCredit()));
	}

	public static final boolean refresh() {
		return (level = dao.getLevelStrategy()) != null;
	}

	public static final LevelStrategy getLevelStrategy() {
		return level;
	}
}
