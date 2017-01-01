package presentation.market.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.ArrayList;
import java.util.List;

import presentation.market.manager.LevelManager;
import utils.info.level.LevelStrategy;

public class LevelManagerImpl implements LevelManager {

	private static LevelManagerImpl instance;

	private LevelStrategy ls;

	private List<Integer> levelCredit;

	public static final LevelManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new LevelManagerImpl();
	}

	public static final LevelManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	@Override
	public void setLevelStrategy(LevelStrategy ls) {
		levelCredit = new ArrayList<Integer>();
		if(ls==null){
			this.ls = new LevelStrategy();
			for (int i = 0; i < 10; i++) {
				levelCredit.add(100);
			}
		}else{
			this.ls = ls;
			
			levelCredit.add(ls.getTreshold(1));
			for (int i = 1; i < 10; i++) {
				levelCredit.add(ls.getTreshold(i + 1) - ls.getTreshold(i));
			}
		}
	}

	@Override
	public List<Integer> getLevelCredit() {
		

		return levelCredit;
	}

	@Override
	public LevelStrategy getOldStrategy() {
		return ls;
	}

}
