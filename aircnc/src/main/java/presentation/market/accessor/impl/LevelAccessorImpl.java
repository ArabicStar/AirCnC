package presentation.market.accessor.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;

import presentation.market.accessor.LevelAccessor;
import utils.info.level.LevelStrategy;

public class LevelAccessorImpl implements LevelAccessor {

	private static LevelAccessor instance;

	private List<Integer> levelCredit;

	private LevelStrategy ls;

	public static final LevelAccessor launch() {
		if (instance != null) {
			throw duplicateSingletonEx();
		}
		return instance = new LevelAccessorImpl();
	}

	public static final LevelAccessor getInstance() {
		if (instance == null) {
			throw singletonNotExistsEx();
		}
		return instance;
	}

	@Override
	public void setLevelCredit(List<Integer> list) {
		this.levelCredit = list;
	}

	@Override
	public LevelStrategy getStrategy() {
		if (ls == null)
			return null;
		int temp = 0;
		for (int i = 0; i < 10; i++) {
			temp += levelCredit.get(i);
			ls.setThreshold(i, temp);
		}

		return ls;
	}

	@Override
	public void setOldStrategy(LevelStrategy old) {
		this.ls = old;

	}

}
